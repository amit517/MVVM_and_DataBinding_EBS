package com.example.mvvmanddatabinding.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmanddatabinding.model.Event;
import com.example.mvvmanddatabinding.repository.EventRepo;

import java.util.List;

public class EventViewModel extends AndroidViewModel {
    private EventRepo eventRepo;
    public EventViewModel(@NonNull Application application) {
            super(application);
        eventRepo = new EventRepo(application);
    }

    public MutableLiveData<String>addEvent(Event event){
        return eventRepo.addEvent(event);
    }

    public  MutableLiveData<List<Event>> getAllEvents(){
        return eventRepo.getAllEvents();
    }
}
