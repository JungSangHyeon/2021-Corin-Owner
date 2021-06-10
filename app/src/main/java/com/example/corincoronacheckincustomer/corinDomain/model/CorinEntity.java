package com.example.corincoronacheckincustomer.corinDomain.model;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorinEntity {

    // Constructor
    public CorinEntity() {
        this.users = new ArrayList<>();
    }

    // Attribute
    private ArrayList<UserEntity> users;
}
