package com.cy.spcdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudNetflixEmail8082 {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNetflixEmail8082.class, args);
    }
}
