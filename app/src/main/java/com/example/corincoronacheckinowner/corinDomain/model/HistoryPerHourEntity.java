package com.example.corincoronacheckinowner.corinDomain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HistoryPerHourEntity {

    // Attribute
    private int hour, totalVisitor, manCount, womenCount;
    private int[] ageGroupCount;

    // Constructor
    public HistoryPerHourEntity(int hour) {
        this.hour=hour;
        this.ageGroupCount = new int[6];
    }

    public void upTotalVisitor(){this.totalVisitor++;}
    public void upManCount(){this.manCount++;}
    public void upWomenCount(){this.womenCount++;}
}
