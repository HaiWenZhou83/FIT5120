package com.example.assignment3.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.assignment3.dao.FitnessDAO;
import com.example.assignment3.entity.Fitness;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Fitness.class}, version = 1, exportSchema = false)
public abstract class FitnessDatabase extends RoomDatabase {
    public abstract FitnessDAO fitnessDAO();
    private static FitnessDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 2;

    public static final ExecutorService databaseWriteExecutor = Executors
            .newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized FitnessDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    FitnessDatabase.class, "FitnessDatabase").fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
