package cn.lyj.common.web;

import cn.lyj.common.exception.Error;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */
public class R
{
    private static final int SUCCESS_CODE = 0;
    private static final int ERROR_CODE = -1;
    private static final String SUCCESS_R = "操作成功！";
    private static final String ERROR_R = "操作失败！";


    private int code;
    private String msg;
    private Object data;

    public R() {}

    public static R error(Error error)
    {
        return error(error, "");
    }

    public static R error(Error error, String tip)
    {
        R r = new R();
        r.setCode(error.getCode());
        r.setMsg(error.getErrMsg() + " " + tip);
        return r;
    }


    public static R success()
    {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setMsg(SUCCESS_R);
        return r;
    }

    public static R success(Object data)
    {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setMsg(SUCCESS_R);
        r.setData(data);
        return r;
    }

    public static R success(String msg)
    {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setMsg(msg);
        return r;
    }

    /**
     *
     * @param code：
     *            0：连接成功
     *            1：消息发送成功
     *            2：接收用户消息
     * @param msg
     * @return
     */
    public static R success(int code,String msg)
    {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setMsg(msg);
        return r;
    }

    /**
     *
     * @param code：
     *            0：连接成功
     *            1：消息发送成功
     *            2：接收用户消息
     * @param data
     * @return
     */
    public static R success(int code,Object data)
    {
        R r = new R();
        r.setCode(code);
        r.setData(data);
        return r;
    }

    public static R error()
    {
        R r = new R();
        r.setCode(ERROR_CODE);
        r.setMsg(ERROR_R);
        return r;
    }

    public static R error(String msg)
    {
        R r = new R();
        r.setCode(ERROR_CODE);
        r.setMsg(msg);
        return r;
    }

    public static R error(int code, String msg)
    {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }
}
