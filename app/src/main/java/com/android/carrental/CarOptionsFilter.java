package com.android.carrental;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.carrental.model.CarModel;
import com.android.carrental.model.Station;
import com.android.carrental.view.CarModels;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CarOptionsFilter extends AppCompatActivity implements View.OnClickListener {

    private Button search_cars;
    private Button date_selector;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private TextView car_type;
    private Button start_time_selector;
    private Button end_time_selector;
    private Button car_type_selector;
    private Calendar calendar;
    private static final DecimalFormat FORMATTER = new DecimalFormat("00");
    private static final String AM = "AM";
    private static final String PM = "PM";
    private static String TIME_AM_PM = "";
    private static final String TIME_SEPARATOR = ":00";
    private static final String TIME_DIVIDER = "12";
    public static final String DATE_FORMAT = "dd MMM yyyy";
    private static final int REQUEST_CODE = 1;
    private Station selectedStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_options_filter);
        search_cars = (Button) findViewById(R.id.searchCar);
        date_selector = (Button) findViewById(R.id.date_selector);
        start_time_selector = (Button) findViewById(R.id.start_time);
        end_time_selector = (Button) findViewById(R.id.end_time);
        car_type_selector = (Button) findViewById(R.id.car_type_selector);
        car_type = (TextView) findViewById(R.id.car_type);
        calendar = Calendar.getInstance();
        search_cars.setOnClickListener(this);
        date_selector.setOnClickListener(this);
        start_time_selector.setOnClickListener(this);
        end_time_selector.setOnClickListener(this);
        car_type_selector.setOnClickListener(this);
        selectedStation = getSelectedStation();
        getSupportActionBar().setTitle("Choose your timings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private Station getSelectedStation() {
        Station selectedStation =  (Station) getIntent().getSerializableExtra("selectedCarDetails");
        return selectedStation;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date_selector:
                selectDate();
                break;
            case R.id.start_time:
                selectStartTime();
                break;
            case R.id.end_time:
                selectEndTime();
                break;
            case R.id.car_type_selector:
                selectCarType();
                break;
        }
    }

    private void selectCarType() {
        Intent intent = new Intent(this, CarModels.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void selectStartTime() {
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                start_time_selector.setText(formatTime(hourOfDay));
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
        timePickerDialog.show();
    }

    private void selectEndTime() {
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                end_time_selector.setText(formatTime(hourOfDay));
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    private void selectDate() {
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date_selector.setText(formatDate(year, month, dayOfMonth));
            }
        }, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        datePickerDialog.show();
    }

    private static String formatDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, day);
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }


    private String formatTime(int hour) {
        Calendar datetime = Calendar.getInstance();
        datetime.set(Calendar.HOUR_OF_DAY, hour);
        if (datetime.get(Calendar.AM_PM) == Calendar.AM)
            TIME_AM_PM = AM;
        else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
            TIME_AM_PM = PM;
        String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ? TIME_DIVIDER : datetime.get(Calendar.HOUR) + "";
        return strHrsToShow + TIME_SEPARATOR + " " + TIME_AM_PM;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            CarModel carModel = (CarModel) data.getSerializableExtra("carmodel");
            car_type.setText(carModel.getName());
        }
    }

}
