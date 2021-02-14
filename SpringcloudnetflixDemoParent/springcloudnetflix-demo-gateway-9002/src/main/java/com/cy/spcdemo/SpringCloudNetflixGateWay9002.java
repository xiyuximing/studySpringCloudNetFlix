package com.cy.spcdemo;

import com.cy.spcdemo.fliter.BlastRequestFilter;
import com.cy.spcdemo.fliter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
public class SpringCloudNetflixGateWay9002 {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNetflixGateWay9002.class, args);
    }

    @Autowired
    private BlastRequestFilter blastRequestFilter;

    @Autowired
    private TokenFilter tokenFilter;

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        r -> r.path("/api/user/register/**")
                                .filters(f -> f.stripPrefix(1)
                                        .filter(blastRequestFilter)
                                        )
                                .uri("lb://spring-cloud-user-server")
                                .order(0)
                                .id("user-customer-router")
                )
                .route(r -> r.path("/api/email/**")
                        .filters(f -> f.stripPrefix(1)
                                .filter(tokenFilter))
                        .uri("lb://spring-cloud-email-server")
                        .order(0)
                        .id("email-token-router")
                )
                .build();
    }

}