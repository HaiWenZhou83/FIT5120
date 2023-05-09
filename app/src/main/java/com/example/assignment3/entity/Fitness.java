package com.example.assignment3.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Fitness {
    @PrimaryKey (autoGenerate = true)
    public int fid;

    @ColumnInfo(name = "fitness_name")
    @NonNull
    public String fitnessName;

    public int time;

    public Fitness( @NonNull String fitnessName, int time) {
        this.fitnessName = fitnessName;
        this.time = time;
    }
}
