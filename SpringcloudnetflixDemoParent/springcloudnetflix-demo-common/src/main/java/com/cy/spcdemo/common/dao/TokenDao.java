package com.cy.spcdemo.common.dao;

import com.cy.spcdemo.common.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TokenDao extends JpaRepository<Token, Integer>, JpaSpecificationExecutor<Token> {
}