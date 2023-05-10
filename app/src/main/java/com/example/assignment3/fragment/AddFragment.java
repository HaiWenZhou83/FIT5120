package com.example.assignment3.fragment;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment3.R;
import com.example.assignment3.adapter.RecyclerViewAdapter;
import com.example.assignment3.databinding.AddFragmentBinding;
import com.example.assignment3.entity.Fitness;
import com.example.assignment3.model.FitnessResult;
import com.example.assignment3.viewmodel.FitnessViewModel;
import com.example.assignment3.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddFragment extends Fragment {
    private AddFragmentBinding addBinding;

    private RecyclerView.LayoutManager layoutManager;
    private List<FitnessResult> units;
    private RecyclerViewAdapter adapter;

    private FitnessViewModel fitnessViewModel;

    public AddFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addBinding = AddFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
        SharedViewModel model = new
                ViewModelProvider(getActivity()).get(SharedViewModel.class);

        units = new ArrayList<FitnessResult>();
        units = FitnessResult.createContactsList();
        adapter = new RecyclerViewAdapter(units);

        addBinding.recyclerView.addItemDecoration(new DividerItemDecoration(addBinding.recyclerView.getContext(),
                LinearLayoutManager.VERTICAL));
        addBinding.recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getContext());
        addBinding.recyclerView.setLayoutManager(layoutManager);

//        String[] fitnessItems = {"Aerobic exercise", "Shoulder press", "Bench press"};
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(addBinding.fitnessName.getContext(),
                R.array.fitnessItems, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        addBinding.spinner.setAdapter(adapter);

        addBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object choice = parent.getItemAtPosition(position);
                //parent.getItemAtPosition(position);
                Toast.makeText(addBinding.getRoot().getContext(), "your choice is "+choice,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fitnessViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getActivity().getApplication()).create(FitnessViewModel.class);

//        fitnessViewModel.getAllFitness().observe(getViewLifecycleOwner(), new Observer<List<Fitness>>() {
//            @Override
//            public void onChanged(@Nullable final List<Fitness> fitnesses) {
//
//                String fitnessName = "";
//                int fT = 0;
//                for (Fitness temp : fitnesses) {
//                    fitnessName = temp.fitnessName;
//                    fT = temp.time;
//                }
//                saveData(fitnessName, fT);
//            }
//        });

        addBinding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stime = addBinding.editText.getText().toString();
                String unit = addBinding.editFtName.getText().toString();
                if (!stime.isEmpty() ) {
                    model.setMessage(stime);
                }

                int newtime = Integer.parseInt(stime);
                Fitness fitness = new Fitness(unit, newtime);
                fitnessViewModel.insert(fitness);

                if (!unit.isEmpty() || !stime.isEmpty()) {

                    saveData(unit, newtime);
                }
            }
        });
        addBinding.clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBinding.editText.setText("");
                addBinding.editFtName.setText("");
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
        addBinding = null;
    }
}
