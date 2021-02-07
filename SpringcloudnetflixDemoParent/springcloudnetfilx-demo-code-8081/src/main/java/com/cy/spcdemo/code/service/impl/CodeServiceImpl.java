package com.cy.spcdemo.code.service.impl;

import com.cy.spcdemo.code.client.EmailClient;
import com.cy.spcdemo.code.service.CodeService;
import com.cy.spcdemo.common.dao.AuthCodeDao;
import com.cy.spcdemo.common.dao.TokenDao;
import com.cy.spcdemo.common.entity.AuthCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private AuthCodeDao authCodeDao;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private EmailClient emailClient;

    @Override
    public boolean createCode(String email) {
        String code = (int)((Math.random()*9+1)*100000) + "";
        AuthCode authCode = new AuthCode();
        authCode.setEmail(email);
        authCode.setCode(code);
        authCode.setCreatetime(new Date());
        Date expireDate = new Date(System.currentTimeMillis() + 1000 * 60 * 10);
        authCode.setExpiretime(expireDate);
        authCodeDao.save(authCode);
        return emailClient.sendEmail(email, code);
    }

    @Override
    public Integer validate(String email, String code) {
        AuthCode authCode = new AuthCode();
        authCode.setEmail(email);
        List<AuthCode> lists = authCodeDao.findAll(Example.of(authCode), new Sort(Sort.Direction.DESC, "expiretime"));
        //校验验证码是否正确，0正确1错误2超时
        if (lists == null || lists.isEmpty()) {
            return 1;
        }
        authCode = lists.get(0);
        if (!Objects.equals(authCode.getCode(), code)) {
            return 1;
        } else if (authCode.getExpiretime().after(new Date())) {
            return 0;
        } else {
            return 2;
        }
    }
}
