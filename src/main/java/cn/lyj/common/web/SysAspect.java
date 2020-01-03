package cn.lyj.common.web;

import com.alibaba.fastjson.JSON;
import cn.lyj.common.utils.DateTimeUtils;
import cn.lyj.common.utils.ServletUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *<p>
 *
 *
 *</p>
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */
@Aspect
@Component
public class SysAspect
{
    private static final Logger log = LoggerFactory.getLogger(SysAspect.class);

    private ThreadLocal<LocalDateTime> threadLocal = new ThreadLocal<>();

    /**
     * 方法规则拦截
     */
    @Pointcut("execution(* cn.lyj.core.controller.*.*(..))")
    public void controllerAspect()
    {
    }

    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("controllerAspect()")
    public void befor(JoinPoint joinPoint)
    {
        HttpServletResponse response = ServletUtils.getResponse();
        HttpServletRequest request = ServletUtils.getRequest();
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, DELETE, PUT, OPTIONS");
        response.addHeader(
                "Access-Control-Allow-Headers",
                "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Accept");


        System.out.println();
        log.warn("|--------------------------- 线程开始：  " + Thread.currentThread().getName() + " --------------------------|");
        threadLocal.set(LocalDateTime.now());
        log.info("--------------#  {}  url-->  {}", Thread.currentThread().getName(), request.getRequestURL());
        Map<String, String[]> params = request.getParameterMap();
        log.info("--------------#  {}  参数-->  {}", Thread.currentThread().getName(), JSON.toJSONString(params));
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String type = request.getMethod();
        log.info("--------------#  {}  请求的方法--> " + className + "/" + methodName + "()" + "/" + type, Thread.currentThread().getName());
    }

    /**
     *
     * 后置通知
     * @aram joinPoint
     */
    @After(value = "controllerAspect()")
    public void after(JoinPoint joinPoint)
    {
        LocalDateTime localDateTime = threadLocal.get();
        log.warn("耗时：" + DateTimeUtils.betweenTwoTime(localDateTime, LocalDateTime.now(), ChronoUnit.MILLIS) + "ms " + DateTimeUtils.betweenTwoTime(localDateTime, LocalDateTime.now(), ChronoUnit.SECONDS) + "s");
        log.info("--------------#  {} 请求结束 " + LocalDateTime.now() + "   ------------------------#", Thread.currentThread().getName());
        System.out.println();
    }

    /**
     * 方法正常运行后 执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(value = "controllerAspect()")
    public void AfterReturning(JoinPoint joinPoint)
    {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 方法正常执行后执行插入日志记录
        this.handelLog(joinPoint, request, null);
    }

    /**
     * 环绕通知
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Object proceed = joinPoint.proceed();
        log.info("--------------#  {}  返回参数  + " + JSON.toJSONString(proceed) + " ----------------------------#", Thread.currentThread().getName());
        return proceed;
    }

    /**
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e)
    {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        this.handelLog(joinPoint, request, e);
    }


    /**
     * 保存日志线程
     */
    private static class SaveLogThread implements Runnable
    {

        @Override
        public void run()
        {

        }
    }


    /**
     * 日志处理
     * @param joinPoint
     * @param request
     * @param e
     */
    public void handelLog(JoinPoint joinPoint, HttpServletRequest request, Throwable e)
    {
        if (e != null)
        {
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            String type = request.getMethod();

            String reqClassMethod = className + "/" + methodName + "()" + "/" + type;

            log.error("---------------------- 异常消息打印开始------------------------");

            if (e.getStackTrace().length > 0)
            {
                StackTraceElement stackTraceElement = e.getStackTrace()[0];
                int lineNumber = stackTraceElement.getLineNumber();
                String fileName = stackTraceElement.getFileName();
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                e.printStackTrace(printWriter);

                log.error("请求url:{}", request.getRequestURI());
                log.error("请求参数:{}", JSON.toJSONString(request.getParameterMap()));
                log.error("请求方法:{}", reqClassMethod);
                log.error("错误文件:{},{},{}", fileName, lineNumber + "行", "出现错误");
                log.error("错误信息:{}", stringWriter.toString());
            }
            log.error("---------------------- 异常信息打印结束------------------------");
        }
    }
}
