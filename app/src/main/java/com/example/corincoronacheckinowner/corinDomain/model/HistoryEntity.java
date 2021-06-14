package com.example.corincoronacheckinowner.corinDomain.model;

import com.example.corincoronacheckinowner.corinDomain.Constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HistoryEntity {

    // Attribute
    private long inTime;
    private String name;
    private int age;
    private Constant.Gender gender;
}
