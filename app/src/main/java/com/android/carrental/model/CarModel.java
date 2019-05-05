package com.android.carrental.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class CarModel implements Serializable {

    private String name;

    public CarModel() {

    }

    public CarModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
