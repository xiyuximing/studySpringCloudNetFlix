package com.cy.spcdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudNetflixEureka8762 {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNetflixEureka8762.class, args);
    }
}
