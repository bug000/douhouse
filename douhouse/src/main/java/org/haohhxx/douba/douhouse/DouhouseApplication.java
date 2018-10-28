package org.haohhxx.douba.douhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhenyuan_hao@163.com
 * 发布到 tomcat  需要继承 SpringBootServletInitializer
 */
@Configuration
@ComponentScan
@EnableScheduling
@SpringBootApplication
public class DouhouseApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DouhouseApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DouhouseApplication.class, args);
    }

}
