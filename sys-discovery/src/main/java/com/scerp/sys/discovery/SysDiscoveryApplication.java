package com.scerp.sys.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableEurekaServer
public class SysDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysDiscoveryApplication.class, args);
    }

    @EnableWebSecurity
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().ignoringAntMatchers(
                    "/eureka/**",
                    "/actuator/**"
            );
            super.configure(http);
        }
    }

}
