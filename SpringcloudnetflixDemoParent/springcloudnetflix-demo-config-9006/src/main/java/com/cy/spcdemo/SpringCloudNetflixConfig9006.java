package com.cy.spcdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringCloudApplication
@EnableConfigServer
public class SpringCloudNetflixConfig9006 {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNetflixConfig9006.class, args);
    }
}
