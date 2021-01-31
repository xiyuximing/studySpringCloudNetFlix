package com.cy.spcdemo.user.service;

import com.cy.spcdemo.user.entity.request.LoginRequest;
import com.cy.spcdemo.user.entity.request.RegisterRequest;

public interface UserService {

    boolean login(LoginRequest loginRequest);

    boolean register(RegisterRequest registerRequest);

    /**
     * 是否已经注册
     * @param email
     * @return
     */
    boolean isRegistered(String email);

}
