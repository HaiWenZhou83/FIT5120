package com.example.assignment3.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    public SharedViewModel(){
        mText = new MutableLiveData<>();
    }
    public void setMessage(String message) {
        mText.setValue(message);
    }
    public LiveData<String> getText() {
        return mText;
    }
}
