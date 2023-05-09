package com.example.assignment3.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.assignment3.entity.Fitness;

import java.util.List;

@Dao
public interface FitnessDAO {

    @Query("SELECT * FROM fitness ORDER BY fitness_name ASC")
    LiveData<List<Fitness>> getAll();

    @Query("SELECT * FROM fitness WHERE fitness_name = :fitName LIMIT 1")
    Fitness findByfitName(String fitName);

    @Insert
    void insert(Fitness fitness);
}
