package com.cy.sycdemo.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "lagou_auth_code")
public class AuthCode {

    @Id
    private Integer id;

    private String email;

    private String code;

    private Date createtime;

    private Date expiretime;

}
