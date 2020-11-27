package com.twenty.four.common.core.enums;

/**
 * @description: 请求返回enum
 * @author: chendong
 * @create: 2020/11/27 11:46
 */
public enum ResultCode {

    SUCCESS(200,"成功"),
    FAILURE(201,"失败"),
    NOT_QUERIED(202,"未查询到数据"),

    NO_PERMISSION(403,"没有权限"),
    NO_AUTH(401,"未登录"),
    NOT_FOUND(404, "未找到该资源"),
    INTERNAL_SERVER_ERROR(500, "服务器无响应"),
    SERVER_MAXFLOW_ERROR(900, "到达服务响应最大数"),

    PARAM_IS_INVALID(1001,"参数不合法"),
    PARAM_IS_BLANK(1002,"参数为空"),
    PARAM_TYPE_BAND_ERROR(1003,"参数为空"),
    PARAM_NOT_COMPLETE(1004,"参数不完整"),
    PARAM_TABLE_NOT_EXIST(1005,"不存在的表"),
    PARAM_TABLE_NOT_CI(1006,"查询的表不是配置项"),
    PARAM_TABLE_IS_CI(1007,"查询的表是配置项"),
    PARAM_TABLE_HAS_CHILD(1008,"指定的表包含子表"),
    PARAM_COLUMN_FORBIDDEN_MODIFY(1009,"字段不允许修改"),
    PARAM_REQUIRED_IS_BLANK(1010,"必要字段为空"),
    PARAM_IS_UNIQUE(1010,"唯一字段重复"),
    PARAM_PART_IS_INVALID(1011,"部分参数不合法，主要的实例已经保存"),

    USER_NOT_SIGNIN(2001,"用户未登录，访问路径需要登录"),
    USER_SIGIN_ERROR(2002,"账号不存在或者账号密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003,"账号被禁用"),
    USER_NOT_EXSIT(2004,"账号不存在"),
    USER_HAS_EXISTED(2005,"账号已存在"),
    USER_NO_PERMISSION(2006,"对表或者实例无权限"),
    USER_NO_PERMISSION_READ(2007,"对表或者实例无读取权限"),
    USER_NO_PERMISSION_WRITE(2008,"对表或者实例无更新权限"),
    USER_NO_PERMISSION_DELETE(2009,"对表或者实例无删除权限,或者表、实例不存在"),


    FILE_NOT_EXISTS(3001,"文件不存在"),
    FILE_NO_PERMISSION_MAKE(3002,"创建文件/文件夹失败"),
    FILE_NO_SELECT_FILES(3003,"无选择上传的文件"),
    FILE_IO_EXCEPTION(3004,"文件IO错误"),

    SERV_DELETE_ERRO(5900,"删除指定数据错误"),
    SERV_SQL_ERRO(5901,"SQL语句错误"),
    SERV_SQL_DATA_INTEGRITY_VIOLATION(5902,"SQL数据完整性错误"),
    SERV_SQL_DUPLICATE(5903,"插入数据时，主键或者索引重复"),
    SERV_RESPONSE_PACK_ERROR(5904,"封装返回JSON错误"),
    SERV_NOT_SUPPORT_METHOD(5998,"服务器不支持的方法"),
    SERV_UNKNOWN_EXCEPTION(5999,"服务器发生未知错误")

    ;
    //#1000～1999 区间表示参数错误
    //#2000～2999 区间表示用户错误
    //#3000～3999 区间表示接口异常
    //#4000～4999 区间表示客户端异常
    //#5000～5999 区间表示服务端异常
    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code(){
        return code;
    }

    public String message(){
        return message;
    }

    public void setMessage(String message){this.message = message; }


}
