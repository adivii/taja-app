package com.utstam.taja;

public class Event {
    int id;
    String event_title, event_place, event_date;

    public Event(int id, String event_title, String event_place, String event_date) {
        this.id = id;
        this.event_title = event_title;
        this.event_place = event_place;
        this.event_date = event_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getEvent_place() {
        return event_place;
    }

    public void setEvent_place(String event_place) {
        this.event_place = event_place;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }
}
