package com.scerp.sys.route;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SysRouteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysRouteApplication.class, args);
    }

}
