package com.example.assignment3.model;

import java.util.ArrayList;
import java.util.List;

public class FitnessResult {
    private String mUnit;
    private int mTime;

    public FitnessResult(String unit, int time) {
        mUnit = unit;
        mTime = time;
    }

    public String getmUnit(){
        return mUnit;
    }

    public int getmTime() {
        return mTime;
    }

    public static List<FitnessResult> createContactsList() {
        List<FitnessResult> units = new ArrayList<>();
//        units.add(new FitnessResult("Running", 30));
//        units.add(new FitnessResult("Sit-ups", 30));
        return units;
    }
}
