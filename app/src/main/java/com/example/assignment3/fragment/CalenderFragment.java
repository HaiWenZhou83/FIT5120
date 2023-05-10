package com.example.assignment3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.assignment3.databinding.CalenderFragmentBinding;

import java.util.Calendar;

public class CalenderFragment extends Fragment {

    private CalenderFragmentBinding binding;

    public CalenderFragment() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = CalenderFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.synBtn.setOnClickListener(view1 -> {
            String title = binding.etTitle.getText().toString();
            String location = binding.etLocation.getText().toString();
            String description = binding.eventDescription.getText().toString();


            if (!title.isEmpty() && !location.isEmpty() && !description.isEmpty()){

//                Intent intent = new Intent(Intent.ACTION_INSERT);
//                intent.setData(CalendarContract.Events.CONTENT_URI);

                Calendar beginTime = Calendar.getInstance();
                beginTime.set(2023, 6, 1, 7, 30);
                Calendar endTime = Calendar.getInstance();
                endTime.set(2013, 6, 1, 8, 45);


                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                        .putExtra(CalendarContract.Events.TITLE, title)
                        .putExtra(CalendarContract.Events.DESCRIPTION, description)
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
                        .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                        .putExtra(Intent.EXTRA_EMAIL, "123@gmail.com");
                startActivity(intent);

//                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
//                    startActivity(intent);
//                }
//                else {
//                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
//                }
           }
            else {
                Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
