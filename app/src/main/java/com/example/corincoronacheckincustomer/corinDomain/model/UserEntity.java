package com.example.corincoronacheckincustomer.corinDomain.model;

import com.example.corincoronacheckincustomer.corinDomain.Constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserEntity {

    // Attribute
    private String id, pw, userName;
    private int age;
    private Constant.Gender gender;
}
