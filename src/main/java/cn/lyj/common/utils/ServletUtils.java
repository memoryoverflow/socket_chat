package cn.lyj.common.utils;

import cn.lyj.common.web.R;
import com.alibaba.fastjson.JSON;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p>
 *
 *
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */
public class ServletUtils
{

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest()
    {
        HttpServletRequest request = getRequestAttributes().getRequest();
        return request;
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse()
    {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession()
    {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes()
    {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 将字符串渲染到客户端
     *
     * @return null
     */
    public static void out(R r)
    {
        PrintWriter out = null;
        try
        {
            HttpServletResponse response = getResponse();
            //设置编码
            response.setCharacterEncoding("UTF-8");
            //设置返回类型
            response.setContentType("application/json");
            out = response.getWriter();
            //输出
            response.getWriter().write(JSON.toJSONString(r));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            out.flush();
            out.close();
        }

    }


    public static Integer getParamInteger(String name)
    {
        String parameter = getRequest().getParameter(name);
        if (StringUtils.isNotBlank(parameter))
        {
            return Integer.parseInt(parameter);
        }
        return null;
    }
}
