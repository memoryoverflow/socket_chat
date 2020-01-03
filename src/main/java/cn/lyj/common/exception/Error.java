package cn.lyj.common.exception;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */
public enum Error
{
    操作失败(-1, "操作失败"),
    系统异常(1, "系统异常"),
    参数为空异常(2, "参数为空异常"),
    参数异常(14, "参数错误"),
    参数绑定异常(3, "参数绑定异常"),
    文件上传异常(4, "文件上传异常"),
    请求方式错误(5, "请求方式不支持"),
    请求路径异常(6, "请检查url是否正确");

    private int code;
    private String errMsg;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getErrMsg()
    {
        return errMsg;
    }

    public void setErrMsg(String errMsg)
    {
        this.errMsg = errMsg;
    }

    Error(int code, String errMsg)
    {
        this.code = code;
        this.errMsg = errMsg;
    }
}
