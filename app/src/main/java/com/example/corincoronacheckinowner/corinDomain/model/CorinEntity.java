package com.example.corincoronacheckinowner.corinDomain.model;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorinEntity {

    // Attribute
    private ArrayList<HistoryEntity> historyEntities;

    // Constructor
    public CorinEntity() {
        this.historyEntities = new ArrayList<>();
    }
}
