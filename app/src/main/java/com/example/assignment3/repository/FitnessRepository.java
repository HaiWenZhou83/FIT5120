package com.example.assignment3.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.assignment3.dao.FitnessDAO;
import com.example.assignment3.database.FitnessDatabase;
import com.example.assignment3.entity.Fitness;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class FitnessRepository {
    private FitnessDAO fitnessDAO;
    private LiveData<List<Fitness>> allFitness;

    public FitnessRepository(Application application) {
        FitnessDatabase db = FitnessDatabase.getInstance(application);
        fitnessDAO =db.fitnessDAO();
        allFitness = fitnessDAO.getAll();
    }

    public LiveData<List<Fitness>> getAllFitness() {
        return allFitness;
    }

    public void insert(final Fitness fitness) {
        FitnessDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                fitnessDAO.insert(fitness);
            }
        });
    }

    public CompletableFuture<Fitness> findByFNFuture(final String fitname) {
        return CompletableFuture.supplyAsync(new Supplier<Fitness>() {
            @Override
            public Fitness get() {
                return fitnessDAO.findByfitName(fitname);
            }
        }, FitnessDatabase.databaseWriteExecutor);
    }
}
