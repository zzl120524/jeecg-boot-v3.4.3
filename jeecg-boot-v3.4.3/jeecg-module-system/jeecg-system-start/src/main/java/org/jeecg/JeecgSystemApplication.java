package org.jeecg;

import com.sdt.omap.server.auth.AuthServer;
import com.sdt.omap.server.control.ControlServer;
import com.sdt.omap.server.report.ReportServer;
import com.sdt.omap.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
* 单体启动类
* 报错提醒: 未集成mongo报错，可以打开启动类上面的注释 exclude={MongoAutoConfiguration.class}
*/
@Slf4j
@SpringBootApplication
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class JeecgSystemApplication extends SpringBootServletInitializer {

    private static ApplicationContext context = null;


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JeecgSystemApplication.class);
    }

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(JeecgSystemApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = oConvertUtils.getString(env.getProperty("server.servlet.context-path"));
        log.info("\n----------------------------------------------------------\n\t" +
                "Application Jeecg-Boot is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "Swagger文档: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");


        try {
            String authPort = env.getProperty("fac.auth-port");
            System.out.println(authPort);
            AuthServer server = new AuthServer();
            server.listen(new InetSocketAddress(Constants.AUTH_PORT));

        } catch (Exception e) {
            e.printStackTrace();
        }

       try{

           ControlServer server = new ControlServer();
           server.listen(new InetSocketAddress(Constants.CONTROL_PORT));
       }catch (Exception e) {
           e.printStackTrace();
       }

        try {

            ReportServer server = new ReportServer();
            server.listen(new InetSocketAddress(Constants.REPORT_PORT));
            log.info("ReportServer 监听启动成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}