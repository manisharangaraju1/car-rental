package com.android.carrental;

public class CarModel {

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
