package com.android.carrental.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.carrental.CarBookingDashboard;
import com.android.carrental.R;
import com.android.carrental.model.Car;
import com.android.carrental.model.CarBooking;
import com.android.carrental.model.Station;

import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.BookingsViewHolder> {

    private Context context;
    private List<CarBooking> bookings;
    private Activity activity;

    public BookingsAdapter(Context context, List<CarBooking> bookings, Activity activity) {
        this.bookings = bookings;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public BookingsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.booking_item, viewGroup, false);
        return new BookingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookingsViewHolder bookingsViewHolder, int i) {
        final CarBooking booking = bookings.get(i);
        bookingsViewHolder.booked_car.setText(booking.getCar().getName());
        bookingsViewHolder.booking_date.setText(booking.getBookingDate());
        bookingsViewHolder.booking_status.setText("Completed");
        bookingsViewHolder.booking_station.setText(booking.getStation().getAddress());
        bookingsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBookingDetails(booking);
            }
        });
    }

    private void showBookingDetails(CarBooking booking) {
//        Intent intentToCarBooking = new Intent(activity, CarBookingDashboard.class);
//        intentToCarBooking.putExtra("selectedStation", selectedStation);
//        intentToCarBooking.putExtra("selectedCar", availableCar);
//        intentToCarBooking.putExtra("rate", availableCar.getRate());
//        intentToCarBooking.putExtra("startTime", startTime);
//        intentToCarBooking.putExtra("endTime", endTime);
//        intentToCarBooking.putExtra("selectedDate", selectedDate);
//        activity.startActivity(intentToCarBooking);
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public class BookingsViewHolder extends RecyclerView.ViewHolder {

        public TextView booking_date;
        public TextView booked_car;
        public TextView booking_status;
        public TextView booking_station;

        public BookingsViewHolder(View itemView) {
            super(itemView);
            booking_date = itemView.findViewById(R.id.booking_date);
            booked_car = itemView.findViewById(R.id.booking_car);
            booking_status = itemView.findViewById(R.id.booking_status);
            booking_station = itemView.findViewById(R.id.booking_station);
        }
    }

}
