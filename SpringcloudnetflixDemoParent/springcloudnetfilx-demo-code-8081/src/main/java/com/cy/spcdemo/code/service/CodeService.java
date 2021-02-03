package com.cy.spcdemo.code.service;

public interface CodeService {

    boolean createCode(String email);

    Integer validate(String email, String code);

}
