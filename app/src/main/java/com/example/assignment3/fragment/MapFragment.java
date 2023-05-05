package com.example.assignment3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


import com.example.assignment3.databinding.MapFragmentBinding;


public class MapFragment extends Fragment {
    private MapFragmentBinding addBinding;

    public MapFragment() {}

    //MapView mMapView = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        addBinding = MapFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();

//        SDKInitializer.initialize(getActivity().getApplicationContext());
//        View view = inflater.inflate(R.layout.map_fragment, container, false);
//        mMapView = (MapView) view.findViewById(R.id.bmapView);
        return view;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        addBinding = null;
//    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        mMapView.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mMapView.onPause();
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        mMapView.onDestroy();
//    }
}
