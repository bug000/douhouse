package org.haohhxx.douba.douhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhenyuan_hao@163.com
 */
@EnableScheduling
@SpringBootApplication
public class DouhouseApplicationT {

    public static void main(String[] args) {
        SpringApplication.run(DouhouseApplicationT.class, args);
    }

}
