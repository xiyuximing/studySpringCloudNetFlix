package com.cy.sycdemo.dao;

import com.cy.sycdemo.entity.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthCodeDao extends JpaRepository<AuthCode, Integer> {
}