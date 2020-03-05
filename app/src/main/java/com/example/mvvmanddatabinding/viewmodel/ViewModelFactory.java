package com.example.mvvmanddatabinding.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmanddatabinding.model.Event;
import com.example.mvvmanddatabinding.repository.EventRepo;
import com.example.mvvmanddatabinding.viewmodel.EventViewModel;

public class ViewModelFactory extends AndroidViewModel implements ViewModelProvider.Factory {

    EventRepo eventRepo;
    Application application;

    public ViewModelFactory(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(EventViewModel.class)){
            eventRepo = new EventRepo();
            return (T) new EventViewModel(application);
        }
        else {
            throw  new IllegalArgumentException("Unknown Class");
        }


    }
}
