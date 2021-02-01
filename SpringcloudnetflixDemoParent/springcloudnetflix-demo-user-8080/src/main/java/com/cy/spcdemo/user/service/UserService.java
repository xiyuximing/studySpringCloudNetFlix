package com.cy.spcdemo.user.service;

import com.cy.spcdemo.user.entity.request.LoginRequest;
import com.cy.spcdemo.user.entity.request.RegisterRequest;

public interface UserService {

    /**
     * 登陆
     * @param loginRequest
     * @return
     */
    String login(LoginRequest loginRequest);

    /**
     * 注册
     * @param registerRequest
     * @return
     */
    boolean register(RegisterRequest registerRequest);

    /**
     * 是否已经注册
     * @param email
     * @return
     */
    boolean isRegistered(String email);

    String info(String token);
}
