package com.example.assignment3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.assignment3.databinding.RyLayoutBinding;
import com.example.assignment3.model.FitnessResult;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter
        <RecyclerViewAdapter.ViewHolder> {

    private static List<FitnessResult> fitnessResults;

    public RecyclerViewAdapter(List<FitnessResult> fitnessResults) {
        this.fitnessResults = fitnessResults;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {
        RyLayoutBinding binding =
                RyLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder,
                                 int position) {
        final FitnessResult unit = fitnessResults.get(position);
        viewHolder.binding.ftName.setText(unit.getmUnit());
        viewHolder.binding.ftTime.setText(Integer.toString(unit.getmTime()));
        viewHolder.binding.ivItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fitnessResults.remove(unit);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return fitnessResults.size();
    }

    public void addUnits(List<FitnessResult> results) {
        fitnessResults = results;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private RyLayoutBinding binding;

        public ViewHolder(RyLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
