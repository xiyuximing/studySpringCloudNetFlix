package com.cy.spcdemo.dao;

import com.cy.spcdemo.entity.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthCodeDao extends JpaRepository<AuthCode, Integer> {
}