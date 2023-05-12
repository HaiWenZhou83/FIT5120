package com.example.assignment3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.assignment3.Example;
import com.example.assignment3.Main;
import com.example.assignment3.databinding.HomeFragmentBinding;
import com.example.assignment3.viewmodel.SharedViewModel;
import com.example.assignment3.weatherapi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private HomeFragmentBinding binding;
    public HomeFragment(){}

    String url= "https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
    String apikey = "0d5e9cea94675ab4c24aebfacd108c13";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the View for this fragment
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        //-----------------------------  function 1 weather retrofit
        //after user click button, didplay weather by weather api using retrofit.
        binding.weatherBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputCountry = binding.et.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.openweathermap.org/data/2.5/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                weatherapi myapi= retrofit.create(weatherapi.class);
                Call<Example> example = myapi.getweather(binding.et.getText().toString().trim(), apikey);
                example.enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
//                        if (response.code() == 404){
//
//                        } else if (!response.isSuccessful()) {
//                            Toast.makeText(binding.getRoot().getContext(), response.code(), Toast.LENGTH_SHORT).show();
//                        }
                        if (inputCountry.isEmpty()){
                            Toast.makeText(binding.getRoot().getContext(), "Please Enter a valid City", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Example mydata=response.body();
                            Main main = mydata.getMain();
                            Double temp = main.getTemp();
                            Integer temperature = (int)(temp-273.15);
                            binding.weatherTv.setText(String.valueOf(temperature)+ "c");
                        }

                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Toast.makeText(binding.getRoot().getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //              <--------------------------->   funtion 2 youtube display by api retrofit
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}