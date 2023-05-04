package com.example.assignment3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.example.assignment3.R;
import com.example.assignment3.databinding.ViewFragmentBinding;
import com.example.assignment3.viewmodel.SharedViewModel;

public class ViewFragment extends Fragment {
    private ViewFragmentBinding binding;
    public ViewFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        SDKInitializer.initialize(getActivity().getApplicationContext());
//        View view = inflater.inflate(R.layout.map_fragment, container, false);
//        mMapView = (MapView)view.findViewById(R.id.bmapView);

// Inflate the View for this fragment using the binding

        binding = ViewFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        SharedViewModel model = new
                ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                binding.textMessage.setText(s);
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