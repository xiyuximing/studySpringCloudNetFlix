package com.cy.spcdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudNetflixUser8080 {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNetflixUser8080.class, args);
    }
}
