package com.example.mvvmanddatabinding.model;

public class Event {
    private String eventId;
    private String eventName;
    private String eventDestination;
    private long eventDate;
    private double eventBudget;
    private int eventDuration;

    public Event() {
    }

    public Event(String eventName, String eventDestination, long eventDate, double eventBudget, int eventDuration) {
        this.eventName = eventName;
        this.eventDestination = eventDestination;
        this.eventDate = eventDate;
        this.eventBudget = eventBudget;
        this.eventDuration = eventDuration;
    }

    public Event(String eventId, String eventName, String eventDestination, long eventDate, double eventBudget, int eventDuration) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDestination = eventDestination;
        this.eventDate = eventDate;
        this.eventBudget = eventBudget;
        this.eventDuration = eventDuration;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDestination() {
        return eventDestination;
    }

    public long getEventDate() {
        return eventDate;
    }

    public double getEventBudget() {
        return eventBudget;
    }

    public int getEventDuration() {
        return eventDuration;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
