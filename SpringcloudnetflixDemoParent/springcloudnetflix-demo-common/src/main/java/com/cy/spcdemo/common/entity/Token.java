package com.cy.spcdemo.common.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lagou_token")
public class Token {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String token;
}