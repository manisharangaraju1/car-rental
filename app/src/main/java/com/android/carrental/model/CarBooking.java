package com.android.carrental.model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.carrental.R;

import java.util.Date;

public class CarBooking {

    private User user;
    private Station station;
    private String date;
    private String startTime;
    private String endTime;

    public CarBooking(User user, Station station, String date, String startTime, String endTime) {
        this.user = user;
        this.station = station;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }

    public Station getStation() {
        return station;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
