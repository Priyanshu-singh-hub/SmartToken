package com.smarttoken.entity;

import jakarta.persistence.*;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String mobile;
    private Integer tokenNumber;

    public Token() {}

    public Token(String name, String mobile, Integer tokenNumber) {
        this.name = name;
        this.mobile = mobile;
        this.tokenNumber = tokenNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public Integer getTokenNumber() {
        return tokenNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setTokenNumber(Integer tokenNumber) {
        this.tokenNumber = tokenNumber;
    }
}