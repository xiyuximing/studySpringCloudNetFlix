package com.cy.spcdemo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudNetflixUser8080 {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNetflixUser8080.class, args);
    }
}
