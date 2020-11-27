package com.twenty.four.basics.gateway.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName XssHttpServletRequestWrapper
 * @Author chendong
 **/
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String oldValue = super.getParameter(name);
        if(StringUtils.isEmpty(oldValue)){
            return oldValue;
        }
        // 对html特殊字符实现转移
        String newValue = StringEscapeUtils.escapeHtml4(oldValue);
        return newValue;
    }
}
