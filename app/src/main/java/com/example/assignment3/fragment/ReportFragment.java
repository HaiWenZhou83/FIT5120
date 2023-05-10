package com.example.assignment3.fragment;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;

import com.example.assignment3.databinding.ReportFragmentBinding;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class ReportFragment extends Fragment {
    private ReportFragmentBinding binding;
    public ReportFragment(){}
    final String CHART_URL = "";

    private DatePickerDialog datePickerDialog;

    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = ReportFragmentBinding.inflate(inflater,container, false);
        View view = binding.getRoot();

        initDatePicker();
        binding.datePickerEnd.setText(getTodayDate());



        binding.datePickerStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar kal = Calendar.getInstance();
                int year = kal.get(Calendar.YEAR);
                int month= kal.get(Calendar.MONTH);
                int day = kal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_DeviceDefault_Dialog,
                        dateSetListener, year, month, day);

                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yy: " + day + "/" + month + "/" + year);

                String date = day + "/" + month + "/" + year;
                binding.textStarView.setText(date);
            }
        };

        binding.datePickerEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 6766));
        barEntries.add(new BarEntry(1, 4444));
        barEntries.add(new BarEntry(2, 2222));
        barEntries.add(new BarEntry(3, 5555));
        barEntries.add(new BarEntry(4, 1111));
        barEntries.add(new BarEntry(5, 3656));
        barEntries.add(new BarEntry(6, 3435));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Steps");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        List<String> xAxisValues = new ArrayList<>(Arrays.asList("Sun", "Mon", "Tues",
                "Wed", "Thurs", "Fri","Sat"));

        binding.barChart.getXAxis().setValueFormatter(new
                com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));
        BarData barData = new BarData(barDataSet);
        binding.barChart.setData(barData);

        barData.setBarWidth(1.0f);
        binding.barChart.setVisibility(View.VISIBLE);
        binding.barChart.animateY(4000);
//description will be displayed as "Description Label" if not provided
        Description description = new Description();
        description.setText("Daily Steps");
        binding.barChart.setDescription(description);
//refresh the chart
        binding.barChart.invalidate();


        return view;
    }

    private String getTodayDate() {
        Calendar calendar = Calendar.getInstance() ;
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                // update local variable string date
                String date = makeDateString(day, month, year);
                // set the text for birthday button from date picker
                binding.datePickerEnd.setText(date);
            }
        };

        Calendar calendar = Calendar.getInstance() ;
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        // initiate date picker
        datePickerDialog = new DatePickerDialog(getContext(), style, dateSetListener, year, month ,day);
    }

    private String makeDateString(int day, int month, int year) {
        return day+ " "+ getMonthFormat(month) +" "+ year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "Jan";
        if (month == 2)
            return "Feb";
        if (month == 3)
            return "Mar";
        if (month == 4)
            return "Apr";
        if (month == 5)
            return "May";
        if (month == 6)
            return "Jun";
        if (month == 7)
            return "Jul";
        if (month == 8)
            return "Aug";
        if (month == 9)
            return "Sep";
        if (month == 10)
            return "Oct";
        if (month == 11)
            return "Nov";
        if (month == 12)
            return "Dec";

        //default should never happen
        return "Jan";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
