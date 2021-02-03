package com.cy.spcdemo.user.service.impl;

import com.cy.spcdemo.common.dao.AuthCodeDao;
import com.cy.spcdemo.common.dao.TokenDao;
import com.cy.spcdemo.common.entity.AuthCode;
import com.cy.spcdemo.common.entity.Token;
import com.cy.spcdemo.user.dao.UserDao;
import com.cy.spcdemo.user.entity.db.User;
import com.cy.spcdemo.user.entity.request.LoginRequest;
import com.cy.spcdemo.user.entity.request.RegisterRequest;
import com.cy.spcdemo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthCodeDao authCodeDao;

    @Autowired
    private TokenDao tokenDao;

    @Override
    public String login(LoginRequest loginRequest, HttpServletResponse response) {
        User qryUser = new User();
        qryUser.setEmail(loginRequest.getEmail());
        qryUser.setPassword(DigestUtils.md5DigestAsHex(loginRequest.getPassword().getBytes()));
        Example<User> example = Example.of(qryUser);
        List<User> all = userDao.findAll(example);
        if (all == null || all.isEmpty()) {
            return null;
        }
        User user = all.get(0);
        String token = UUID.randomUUID().toString();
        Token t = new Token();
        t.setEmail(loginRequest.getEmail());
        t.setToken(token);
        tokenDao.save(t);
        response.addCookie(new Cookie("token", token));
        return user.getEmail();
    }

    @Override
    public boolean register(RegisterRequest registerRequest) {

        Sort sort = new Sort(Sort.Direction.DESC, "ID");
        List<AuthCode> all = authCodeDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.greaterThan(root.get("expiretime"), new Date());
            Predicate email = criteriaBuilder.equal(root.get("email"), registerRequest.getEmail());
            return criteriaBuilder.and(predicate, email);
        }, sort);
        if (all == null || all.isEmpty()) {
            return false;
        }
        AuthCode code = all.get(0);
        if (!Objects.equals(code.getCode(), registerRequest.getCode())) {
            return false;
        }
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(DigestUtils.md5DigestAsHex(registerRequest.getPassword().getBytes()));
        userDao.save(user);
        return true;
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
        Token t = new Token();
        t.setToken(token);
        Optional<Token> one = tokenDao.findOne(Example.of(t));
        if (one.isPresent()) {
            return one.get().getEmail();
        }
        return null;
    }
}
