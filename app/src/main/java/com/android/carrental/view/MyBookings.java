package com.android.carrental.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.android.carrental.MyAccount;
import com.android.carrental.PaymentMethods;
import com.android.carrental.R;
import com.android.carrental.adapter.BookingsAdapter;
import com.android.carrental.adapter.StationAdapter;
import com.android.carrental.help.Help;
import com.android.carrental.model.CarBooking;
import com.android.carrental.model.Station;
import com.android.carrental.userauth.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyBookings extends AppCompatActivity {

    private RecyclerView bookings_recycler_view;
    private List<CarBooking> bookings;
    private BookingsAdapter bookingsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);
        getSupportActionBar().setTitle("Recent Trips");
        bookings_recycler_view = findViewById(R.id.bookings_recycler_view);
        initWidgets();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initWidgets();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("bookings");
        final String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CarBooking booking = snapshot.getValue(CarBooking.class);
                    if (booking.getUser().equals(user)) {
                        bookings.add(booking);
                    }
                }
                bookingsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initWidgets() {
        bookings_recycler_view.setHasFixedSize(true);
        bookings_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        bookings = new ArrayList<>();
        bookingsAdapter = new BookingsAdapter(getApplicationContext(), bookings, this);
        bookingsAdapter.notifyDataSetChanged();
        bookings_recycler_view.setAdapter(bookingsAdapter);
    }
}
