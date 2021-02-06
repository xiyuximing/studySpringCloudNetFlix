package com.cy.spcdemo.code.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="spring-cloud-email-server")
public interface EmailClient {

    @RequestMapping("/email/{email}/{code}")
    boolean sendEmail(@PathVariable(name = "email") String email, @PathVariable(name = "code") String code);
}
