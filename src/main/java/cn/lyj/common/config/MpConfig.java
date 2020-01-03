package cn.lyj.common.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * <p>
 *  mybatis-plus 配置类
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */
@Configuration
public class MpConfig
{
    /**
     * <br>
     *
     *  mybatis-plus分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor()
    {
        PaginationInterceptor page = new PaginationInterceptor();
        return page;
    }

    /**
     * <br>
     * 逻辑删除 本项目默认 没有配置 逻辑删除
     */
    //@Bean
    public ISqlInjector sqlInjector()
    {
        return new LogicSqlInjector();
    }

    /**
     * <br>
     * sql格式化拦截器
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor()
    {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // 格式化sql语句
        Properties properties = new Properties();
        properties.setProperty("format", "true");
        performanceInterceptor.setProperties(properties);
        return performanceInterceptor;
    }
}
