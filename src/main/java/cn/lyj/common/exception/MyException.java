package cn.lyj.common.exception;

import cn.lyj.common.exception.Error;
import cn.lyj.common.exception.MyException;

/**
 * <p>
 *  自定义异常
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */
public class MyException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 1;

    private Error error;

    public MyException(Error error)
    {
        super(error.getErrMsg());
        this.error = error;
        this.code = error.getCode();
        this.msg = error.getErrMsg();
    }

    public MyException(Error error, String msg)
    {
        super(msg);
        this.error = error;
        this.code = error.getCode();
        this.msg = error.getErrMsg() + " " + msg;
    }

    public MyException()
    {
        super();
    }

    public MyException(String msg)
    {
        super(msg);
        this.msg = msg;
    }

    public MyException(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }


    public MyException(String msg, Throwable e)
    {
        super(msg, e);
        this.msg = msg;
    }

    public MyException(String msg, int code)
    {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public MyException(String msg, int code, Throwable e)
    {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public MyException setMsg(String msg)
    {
        this.msg = msg;
        return this;
    }

    public int getCode()
    {
        return code;
    }

    public MyException setCode(int code)
    {
        this.code = code;
        return this;
    }

    public Error getError()
    {
        return error;
    }
}
