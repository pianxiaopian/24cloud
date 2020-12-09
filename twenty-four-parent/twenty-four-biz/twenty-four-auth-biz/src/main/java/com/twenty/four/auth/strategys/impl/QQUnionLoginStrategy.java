package com.twenty.four.auth.strategys.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.twenty.four.auth.domain.UnionLoginDo;
import com.twenty.four.auth.strategys.UnionLoginStrategy;
import com.twenty.four.common.core.constant.Constants;
import com.twenty.four.common.core.result.Result;
import com.twenty.four.common.core.utils.HttpClientUtils;
import com.twenty.four.common.core.utils.ParamToOtherUtils;
import com.twenty.four.common.core.utils.TokenUtils;
import com.twenty.four.oss.api.OssService;
import com.twenty.four.oss.model.dto.UserInfoDTO;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @description: qq联合登录实现类
 * @author: chendong
 * @create: 2020/12/4 17:08
 */

@Component
@Slf4j
public class QQUnionLoginStrategy implements UnionLoginStrategy {


    @Value("${pian.login.qq.accesstoken}")
    private String qqAccessTokenAddress;
    @Value("${pian.login.qq.openid}")
    private String qqOpenIdAddress;
    @Value("${auth.query.qq}")
    private String qqOpenId;
    @Value("${oss.service.url}")
    private String ossUrl;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private ParamToOtherUtils paramToOtherUtils;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Result unionLoginCallback(HttpServletRequest request, UnionLoginDo unionLoginDo) {
        String code = request.getParameter("code");
        if(StringUtils.isBlank(code)){
            return null;
        }
        String newQQAccessTokenAddress = null;
        // 1.根据授权码获取accessToken
        newQQAccessTokenAddress = qqAccessTokenAddress.replace("{client_id}"
            , unionLoginDo.getAppId()).replace("{client_secret}", unionLoginDo.getAppKey()).
            replace("{code}", code).replace("{redirect_uri}", unionLoginDo.getRedirectUri());
        String resultAccessToken = HttpClientUtils.httpGetResultString(newQQAccessTokenAddress);
        boolean contains = resultAccessToken.contains("access_token=");
        if (!contains) {
            return null;
        }
        String jsonStrByQueryUrl = paramToOtherUtils.getJsonStrByQueryUrl(resultAccessToken);
        JSONObject jsonObject = JSON.parseObject(jsonStrByQueryUrl);
        //解析得到accessToken
        String accessToken = jsonObject.getString("access_token");
        if (StringUtils.isEmpty(accessToken)) {
            return null;
        }
        // 2.根据accessToken获取用户的openid
        String resultQQOpenId = HttpClientUtils.httpGetResultString(qqOpenIdAddress+accessToken);
        if (StringUtils.isEmpty(resultQQOpenId)) {
            return null;
        }
        boolean openid = resultQQOpenId.contains("openid");
        if (!openid) {
            return null;
        }
        String[] array = resultQQOpenId.trim().replace("callback( {", "").
            replace("} );", "").split(",");
        String openIdArray = array[1];
        String[] str = openIdArray.split(":");
        String openId = str[1].trim().replace("\"", "");
        // 3.将openid存入到redis中
        String token = tokenUtils.createToken("qq_union_token", openId, 10L, TimeUnit.MINUTES);
        // 4.将token存入redis中 为了后续关联用户进行传递
        tokenUtils.setOpenId(qqOpenId,token,10L, TimeUnit.MINUTES);
        //判断用户是否关联此qq号
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("columnName", qqOpenId);
        requestEntity.add("openId", openId);
        Result result = restTemplate.postForObject(ossUrl, requestEntity, Result.class);
        return result;
    }

    @Override
    public Result unionLoginCallback(UnionLoginDo unionLoginDo) {
        //先判断redis中是否有授权链接的缓存信息
        String qq_request_address = tokenUtils.getTokenValue("qq_request_address");
        if(StringUtils.isBlank(qq_request_address)){
            //不存在则生成链接
            String requestAddress = unionLoginDo.getRequestAddress();
            UUID uuid = UUID.randomUUID();
            String str = uuid.toString();
            // 去掉"-"符号
            String strUuid = str.replace("-", "");
            //生成链接后缓存到redis中
            tokenUtils.setKey("qq_request_address",requestAddress+strUuid,10L,TimeUnit.MINUTES);
            return Result.ok(requestAddress+strUuid,"获取授权链接成功");
        }
        return Result.ok(qq_request_address,"获取授权链接成功");
    }

}
