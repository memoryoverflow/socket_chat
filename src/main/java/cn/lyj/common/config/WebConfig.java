package cn.lyj.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.core.convert.converter.Converter;
/**
 *<p>
 *
 *
 *</p>
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter
{
    private static final List<String> formarts = new ArrayList<>(4);

    static
    {
        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd HH:mm");
        formarts.add("yyyy-MM-dd HH:mm:ss");
    }

    @Autowired
    RequestMappingHandlerAdapter handlerAdapter;

    @PostConstruct
    public void initEditableAvlidation()
    {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService() != null)
        {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(new GlobalDateConvert());
        }
    }

    /**
     * <br>
     * 配置日期转化器
     */
    class GlobalDateConvert implements Converter<String, Date>
    {


        @Override
        public Date convert(String source)
        {
            String value = source.trim();
            if ("".equals(value))
            {
                return null;
            }
            if (source.matches("^\\d{4}-\\d{1,2}$"))
            {
                return parseDate(source, WebConfig.formarts.get(0));
            }
            else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$"))
            {
                return parseDate(source, WebConfig.formarts.get(1));
            }
            else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$"))
            {
                return parseDate(source, WebConfig.formarts.get(2));
            }
            else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$"))
            {
                return parseDate(source, WebConfig.formarts.get(3));
            }
            else
            {
                throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
            }
        }

        /**
         * 格式化日期
         *
         * @param dateStr String 字符型日期
         * @param format String 格式
         *
         * @return Date 日期
         */
        public Date parseDate(String dateStr, String format)
        {
            Date date = null;
            try
            {

                DateFormat dateFormat = new SimpleDateFormat(format);
                date = dateFormat.parse(dateStr);
            }
            catch (Exception e)
            {

            }
            return date;
        }
    }
}
