package cn.lyj.common.exception;

import cn.lyj.common.web.R;
import cn.lyj.common.exception.Error;
import cn.lyj.common.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * <p>
 *   全局异常拦截器
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends Exception
{
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 参数绑定错误
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R handleRRException(MissingServletRequestParameterException e)
    {
        e.printStackTrace();
        return R.error(Error.参数绑定异常);
    }

    /**
     * GET,POST 等方法
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R handleRRException(HttpRequestMethodNotSupportedException e)
    {
        e.printStackTrace();
        return R.error(Error.请求方式错误, "请试试GET/POST/UPDATE...");
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(MyException.class)
    public R handleRRException(MyException e)
    {
        return R.error(e.getCode(), e.getMsg());
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e)
    {
        // 参数绑定异常
        if (e instanceof BindException)
        {
            BindException bindException = (BindException) e;
            for (ObjectError objectError : bindException.getAllErrors())
            {
                String defaultMessage = objectError.getDefaultMessage();
                return R.error(defaultMessage);
            }
        }
        return R.error(Error.系统异常);
    }

    /**
     * <br>
     * 404 错误
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public R handlerNoFoundException(Exception e)
    {
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        return R.error(Error.请求路径异常);
    }


}
