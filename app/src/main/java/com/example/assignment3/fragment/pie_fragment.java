package com.example.assignment3.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.assignment3.databinding.ChartPieBinding;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class pie_fragment extends Fragment {

    public ChartPieBinding binding;

    public pie_fragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ChartPieBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        List<PieEntry> visitors = new ArrayList<>();
        visitors.add(new PieEntry(30, "Running"));
        visitors.add(new PieEntry(40, "Caidio Training"));
        visitors.add(new PieEntry(30, "Weight Training"));
        visitors.add(new PieEntry(30, "Flexibility Training"));

        PieDataSet pieDataSet = new PieDataSet(visitors, "Visitors");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        binding.pieChart.setData(pieData);
        binding.pieChart.getDescription().setEnabled(false);
        binding.pieChart.setCenterText("Time record (min)");
        binding.pieChart.animate();


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
