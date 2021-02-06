package com.cy.spcdemo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "spring-cloud-user-server")
public interface UserClient {

    @RequestMapping("/info/{token}")
    String info(@PathVariable String token);
}