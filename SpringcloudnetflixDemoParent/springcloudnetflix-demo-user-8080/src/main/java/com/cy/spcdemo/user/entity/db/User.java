package com.cy.spcdemo.user.entity.db;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lagou_user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String password;
}
