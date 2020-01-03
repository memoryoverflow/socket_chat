package cn.lyj;

import cn.lyj.sockect.SockectServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <p>
 *  App 启动入口
 * </p>
 *
 * @author 永健
 * @since 2019-12-26 03:35
 */
@SpringBootApplication
@MapperScan("cn.lyj.core.mapper")
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ConfigurableApplicationContext applicationContext = SpringApplication.run(new Class[]{App.class, SockectServer.class}, args);
        SockectServer.setApp(applicationContext);
    }
}
