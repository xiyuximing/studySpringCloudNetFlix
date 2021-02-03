package com.cy.spcdemo.common.dao;

import com.cy.spcdemo.common.entity.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthCodeDao extends JpaRepository<AuthCode, Integer>, JpaSpecificationExecutor<AuthCode> {
}