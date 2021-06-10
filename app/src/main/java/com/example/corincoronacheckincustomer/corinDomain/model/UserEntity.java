package com.example.corincoronacheckincustomer.corinDomain.model;

import com.example.corincoronacheckincustomer.corinDomain.crossDomain.Constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserEntity {

    // Attribute
    private String id, pw, userName;
    private int age;
    private Constant.Gender gender;
}
