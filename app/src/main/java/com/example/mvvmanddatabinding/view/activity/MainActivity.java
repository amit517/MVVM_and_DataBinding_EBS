package com.example.mvvmanddatabinding.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mvvmanddatabinding.R;
import com.example.mvvmanddatabinding.databinding.ActivityMainBinding;
import com.example.mvvmanddatabinding.model.Event;
import com.example.mvvmanddatabinding.viewmodel.EventViewModel;
import com.example.mvvmanddatabinding.viewmodel.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private EventViewModel eventViewModel;
    private Application application;
    private ViewModelFactory viewModelFacotory;
    private List<Event> eventList;
    final String TAG = "mTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        
        getAllEvents();

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.addNewEventFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddEventActivity.class));
            }
        });




    }

    private void getAllEvents() {
        eventViewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                if(events.size() > 0){
                    eventList =  events;
                    //Here We will notify the Adapter
                    for (Event event: eventList
                         ) {
                        Log.d(TAG, "onChanged: "+event.getEventName());

                    }
                }
            }
        });
    }

    private void init() {
        eventList = new ArrayList<>();
        application = getApplication();
        //viewModelFacotory = new ViewModelFactory(application);
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

    }
}
