package com.cy.spcdemo.user.dao;

import com.cy.spcdemo.user.entity.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
}
