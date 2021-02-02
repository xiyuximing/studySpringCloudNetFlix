package com.cy.spcdemo.user.entity.db;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "lagou_user")
public class User {

    @Id
    private Integer id;

    private String email;

    private String password;
}