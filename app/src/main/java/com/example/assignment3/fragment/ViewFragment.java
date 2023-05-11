package com.example.assignment3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment3.adapter.RecyclerViewAdapter;
import com.example.assignment3.databinding.ViewFragmentBinding;
import com.example.assignment3.entity.Fitness;
import com.example.assignment3.model.FitnessResult;
import com.example.assignment3.viewmodel.FitnessViewModel;
import com.example.assignment3.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class ViewFragment extends Fragment {
    private ViewFragmentBinding binding;

    private RecyclerView.LayoutManager layoutManager;
    private List<FitnessResult> units;
    private RecyclerViewAdapter adapter;

    private FitnessViewModel fitnessViewModel;

    public ViewFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

// Inflate the View for this fragment using the binding

        binding = ViewFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

//        SharedViewModel model = new
//                ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//        model.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                binding.textMessage.setText(s);
//            }
//        });

//        activities=new ArrayList<AimInformation>();
//        activities= AimInformation.createContactsList();
//        adapter =new RecyclerViewAdapter(activities);
//        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this,
//                LinearLayoutManager.VERTICAL) );

        units = new ArrayList<FitnessResult>();
        units = FitnessResult.createContactsList();
        adapter = new RecyclerViewAdapter(units);

        binding.recyclerView.addItemDecoration(new DividerItemDecoration(binding.recyclerView.getContext(),
                LinearLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(layoutManager);

        fitnessViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getActivity().getApplication()).create(FitnessViewModel.class);

        fitnessViewModel.getAllFitness().observe(getViewLifecycleOwner(), new Observer<List<Fitness>>() {
            @Override
            public void onChanged(@Nullable final List<Fitness> fitnesses) {

                String fitnessName = "";
                int fT = 0;
                for (Fitness temp : fitnesses) {
                    fitnessName = temp.fitnessName;
                    fT = temp.time;
                    FitnessResult fitnessResult = new FitnessResult(fitnessName, fT);
                    units.add(fitnessResult);
                    adapter.addUnits(units);
                }
                //saveData(fitnessName, fT);
            }
        });

        return view;


    }

    private void saveData(String unit, int time) {
        FitnessResult fitnessResult = new FitnessResult(unit, time);
        units.add(fitnessResult);
        adapter.addUnits(units);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}