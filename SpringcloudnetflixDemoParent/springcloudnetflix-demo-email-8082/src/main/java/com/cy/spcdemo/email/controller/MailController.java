package com.cy.spcdemo.email.controller;

import com.cy.spcdemo.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/email/{email}/{code}")
    public boolean sendEmail(@PathVariable(name = "email") String email, @PathVariable(name = "code") String code) {
        return emailService.sendEmail(email, code);
    }
}
