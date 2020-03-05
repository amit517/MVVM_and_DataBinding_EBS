package com.example.mvvmanddatabinding.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvvmanddatabinding.R;
import com.example.mvvmanddatabinding.viewmodel.ViewModelFactory;
import com.example.mvvmanddatabinding.databinding.ActivityAddEventBinding;
import com.example.mvvmanddatabinding.model.Event;
import com.example.mvvmanddatabinding.viewmodel.EventViewModel;

public class AddEventActivity extends AppCompatActivity{

    private ActivityAddEventBinding binding;
    private EventViewModel eventViewModel;
    private ViewModelFactory viewModelFacotory;
    private Application application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_event);

        init();

        binding.backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = binding.eventNameET.getText().toString();
                String eventDestination = binding.eventDestinationET.getText().toString();
                double eventBudget = Double.parseDouble(binding.eventBudgetET.getText().toString());
                int eventDuration = Integer.parseInt(binding.eventDurationET.getText().toString());
                addEvent(eventName,eventDestination,eventBudget,eventDuration);
            }
        });

    }

        private void init() {
            application = getApplication();
            viewModelFacotory = new ViewModelFactory(application);
            eventViewModel = new ViewModelProvider(this, viewModelFacotory).get(EventViewModel.class);

    }

    private void addEvent(String eventName, String eventDestination, double eventBudget, int eventDuration) {
        Event event = new Event(eventName,eventDestination,System.currentTimeMillis(),eventBudget,eventDuration);
        eventViewModel.addEvent(event).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String responseCode) {
                if(responseCode.equals("200")){
                    Toast.makeText(AddEventActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }

            }
        });

    }

}
