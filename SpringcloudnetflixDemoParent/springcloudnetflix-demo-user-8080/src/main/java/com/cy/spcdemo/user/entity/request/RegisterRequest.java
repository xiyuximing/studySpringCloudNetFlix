package com.cy.spcdemo.user.entity.request;

import lombok.Data;

@Data
public class RegisterRequest {

    private String code;

    private String email;

    private String password;
}
