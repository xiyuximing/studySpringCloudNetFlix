package com.cy.spcdemo.common.entity;


import lombok.Data;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "lagou_auth_code")
public class AuthCode {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String code;

    private Date createtime;

    private Date expiretime;

}
