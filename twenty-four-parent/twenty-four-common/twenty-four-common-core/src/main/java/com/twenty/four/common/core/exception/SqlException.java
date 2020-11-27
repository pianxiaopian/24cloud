package com.twenty.four.common.core.exception;

/**
 * @description: sql异常
 * @author: chendong
 * @create: 2020/11/27 11:50
 */
public class SqlException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String defaultMessage;

    public SqlException(String module, String code, Object[] args, String defaultMessage)
    {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public SqlException(String module, String code, Object[] args)
    {
        this(module, code, args, null);
    }

    public SqlException(String module, String defaultMessage)
    {
        this(module, null, null, defaultMessage);
    }

    public SqlException(String code, Object[] args)
    {
        this(null, code, args, null);
    }

    public SqlException(String defaultMessage)
    {
        this(null, null, null, defaultMessage);
    }

    public String getModule()
    {
        return module;
    }

    public String getCode()
    {
        return code;
    }

    public Object[] getArgs()
    {
        return args;
    }

    public String getDefaultMessage()
    {
        return defaultMessage;
    }
}
