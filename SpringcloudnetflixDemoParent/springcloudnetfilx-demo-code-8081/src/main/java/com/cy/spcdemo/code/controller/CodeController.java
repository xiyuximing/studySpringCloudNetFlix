package com.cy.spcdemo.code.controller;

import com.cy.spcdemo.code.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @RequestMapping("/create/{email}")
    public boolean createCode(@PathVariable String email) {
        return codeService.createCode(email);
    }

    @RequestMapping("/validate/{email}/{code}")
    public Integer validate(@PathVariable(value = "email") String email, @PathVariable(value = "code") String code) {
        return codeService.validate(email, code);
    }

}