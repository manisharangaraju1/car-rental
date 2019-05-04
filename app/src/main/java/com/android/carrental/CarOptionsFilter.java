package com.android.carrental;

import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CarOptionsFilter extends AppCompatActivity implements View.OnClickListener {

    private Button search_cars;
    private Button date_selector;
    private Button start_time_selector;
    private Button end_time_selector;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    private static final int INTERVAL = 60;
    private TimePicker picker;
    private NumberPicker minutePicker;
    private static final DecimalFormat FORMATTER = new DecimalFormat("00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_options_filter);
        search_cars = (Button) findViewById(R.id.searchCar);
        date_selector = (Button) findViewById(R.id.date_selector);
        start_time_selector = (Button) findViewById(R.id.start_time);
        end_time_selector = (Button) findViewById(R.id.end_time);
        calendar = Calendar.getInstance();
        search_cars.setOnClickListener(this);
        date_selector.setOnClickListener(this);
        start_time_selector.setOnClickListener(this);
        end_time_selector.setOnClickListener(this);
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
        }
    }

    private void selectStartTime() {
    }

    private void selectEndTime() {
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        return sdf.format(date);
    }

    public void setMinutePicker() {
        int numValues = 60 / INTERVAL;
        String[] displayedValues = new String[numValues];
        for (int i = 0; i < numValues; i++) {
            displayedValues[i] = FORMATTER.format(i * INTERVAL);
        }

        View minute = picker.findViewById(Resources.getSystem().getIdentifier("minute", "id", "android"));
        if ((minute != null) && (minute instanceof NumberPicker)) {
            minutePicker = (NumberPicker) minute;
            minutePicker.setMinValue(0);
            minutePicker.setMaxValue(numValues - 1);
            minutePicker.setDisplayedValues(displayedValues);
        }
    }

    public int getMinute() {
        if (minutePicker != null) {
            return (minutePicker.getValue() * INTERVAL);
        } else {
            return picker.getCurrentMinute();
        }
    }

}
