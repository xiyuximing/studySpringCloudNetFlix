package com.cy.spcdemo.dao;

import com.cy.spcdemo.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TokenDao extends JpaRepository<Token, Integer>, JpaSpecificationExecutor<Token> {
}