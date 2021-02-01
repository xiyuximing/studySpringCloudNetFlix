package com.cy.spcdemo.user.service.impl;

import com.cy.spcdemo.user.dao.UserDao;
import com.cy.spcdemo.user.entity.db.User;
import com.cy.spcdemo.user.entity.request.LoginRequest;
import com.cy.spcdemo.user.entity.request.RegisterRequest;
import com.cy.spcdemo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String login(LoginRequest loginRequest) {
        User qryUser = new User();
        qryUser.setEmail(loginRequest.getEmail());
        qryUser.setPassword(DigestUtils.md5DigestAsHex(loginRequest.getPassword().getBytes()));
        Example<User> example = Example.of(qryUser);
        List<User> all = userDao.findAll(example);
        if (all == null || all.isEmpty()) {
            return null;
        }
        User user = all.get(0);
        return user.getEmail();
    }

    @Override
    public boolean register(RegisterRequest registerRequest) {

        return false;
    }

    @Override
    public boolean isRegistered(String email) {
        User qryUser = new User();
        qryUser.setEmail(email);
        Example<User> example = Example.of(qryUser);
        List<User> all = userDao.findAll(example);
        return all != null && !all.isEmpty();
    }

    @Override
    public String info(String token) {
        return null;
    }
}
