package com.example.assignment3.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assignment3.entity.Fitness;
import com.example.assignment3.repository.FitnessRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FitnessViewModel extends AndroidViewModel {
    private FitnessRepository cRepository;
    private LiveData<List<Fitness>> allFitness;

    public FitnessViewModel( Application application) {
        super(application);
        cRepository = new FitnessRepository(application);
        allFitness = cRepository.getAllFitness();
    }

    public CompletableFuture<Fitness> findByFNFuture(final String ftName) {
        return cRepository.findByFNFuture(ftName);
    }

    public LiveData<List<Fitness>> getAllFitness() {
        return allFitness;
    }

    public void insert(Fitness fitness) {
        cRepository.insert(fitness);
    }
}
