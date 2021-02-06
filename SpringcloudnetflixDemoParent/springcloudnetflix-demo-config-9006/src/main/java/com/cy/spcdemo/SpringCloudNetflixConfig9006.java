package com.cy.spcdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class SpringCloudNetflixConfig9006 {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNetflixConfig9006.class, args);
    }
}
