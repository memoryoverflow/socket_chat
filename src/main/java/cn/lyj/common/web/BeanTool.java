package cn.lyj.common.web;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static cn.lyj.common.utils.FilterNull.isNull;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-05-28 14:23
 */
public class BeanTool implements ApplicationContextAware
{
    private static ApplicationContext app;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        app = applicationContext;
    }

    private static ApplicationContext getApplicationContext()
    {
        return app;
    }

    public static <T> T getInstance(String beanName)
    {
        T bean = (T) getApplicationContext().getBean(beanName);
        isNull(bean,"获取不到Bean[ "+beanName+" ]");
        return bean;
    }

    public static <T> T getInstance(Class<T> tClass)
    {
        T bean = getApplicationContext().getBean(tClass);
        isNull(bean,"获取不到Bean[ "+tClass+" ]");
        return bean;
    }

    public static void removeBeanFromIOC(String beanName)
    {
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) getApplicationContext().getAutowireCapableBeanFactory();
        beanFactory.removeBeanDefinition(beanName);
    }

}
