package com.android.carrental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CarSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private Button searchCarsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_search);
        searchCarsButton = (Button) findViewById(R.id.searchCar);
    }

    @Override
    public void onClick(View v) {

    }
}
