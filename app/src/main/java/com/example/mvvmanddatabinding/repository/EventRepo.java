package com.example.mvvmanddatabinding.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmanddatabinding.model.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventRepo {
    private Application application;
    private DatabaseReference databaseReference;


    public EventRepo() {
    }

    public EventRepo(Application application) {
        this.application = application;
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public MutableLiveData<String> addEvent(Event event){
        final MutableLiveData<String> liveData = new MutableLiveData<>();
        //Here is the code of adding event code
        DatabaseReference eventRef = databaseReference.child("events");
        String eventID =eventRef.push().getKey();
        event.setEventId(eventID);

        eventRef.child(eventID).setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    liveData.postValue("200");
                }
            }
        });

        return liveData;
    }

    public  MutableLiveData<List<Event>> getAllEvents(){
        final MutableLiveData<List<Event>> liveData = new MutableLiveData<>();

        // Here is the code of getting all events

        DatabaseReference eventsRef = databaseReference.child("events");

        eventsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    List<Event> eventList= new ArrayList<>();

                    for (DataSnapshot data:dataSnapshot.getChildren()
                         ) {
                        Event event = data.getValue(Event.class);
                        eventList.add(event);
                    }

                    liveData.postValue(eventList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return liveData;
    }

}
