package com.cy.spcdemo.user.controller;

import com.cy.spcdemo.user.entity.request.LoginRequest;
import com.cy.spcdemo.user.entity.request.RegisterRequest;
import com.cy.spcdemo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登陆
     * @return
     */
    @RequestMapping("/login/{email}/{password}")
    public String login(@PathVariable(value = "email") String email, @PathVariable(value = "password") String password, HttpServletResponse response) {
        LoginRequest loginRequest = new LoginRequest(email, password);
        return userService.login(loginRequest, response);
    }


    @RequestMapping("/register/{email}/{password}/{code}")
    public boolean register(@PathVariable(value = "email") String email, @PathVariable(value = "password") String password, @PathVariable(value = "code") String code) {
        RegisterRequest registerRequest = new RegisterRequest();
        return userService.register(registerRequest);
    }

    /**
     * 是否已经注册
     * @param email
     * @return
     */
    @RequestMapping("/isRegistered/{email}")
    public boolean isRegistered(@PathVariable String email) {
        return userService.isRegistered(email);
    }

    @RequestMapping("/info/{token}")
    public String info(@PathVariable String token) {
        return userService.info(token);
    }
}