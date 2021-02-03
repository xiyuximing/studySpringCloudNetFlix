package com.cy.spcdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
public class SpringCloudNetflixCode8081 {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNetflixCode8081.class, args);
    }
}
