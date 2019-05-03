package com.android.carrental;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NearbyCarStations extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Station> stations;
    private StationAdapter stationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_car_stations);
        recyclerView = findViewById(R.id.staions_recycler_view);
        initWidgets();
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("stations");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Station user = snapshot.getValue(Station.class);
                    stations.add(user);
                }
                Collections.sort(stations, new Comparator<Station>() {
                    @Override
                    public int compare(Station station1, Station station2) {
                        return Double.compare(Double.parseDouble(station1.getDistance()), (Double.parseDouble(station2.getDistance())));
                    }
                });
                stationAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void initWidgets() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        stations = new ArrayList<>();
        stationAdapter = new StationAdapter(getApplicationContext(), stations);
        stationAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(stationAdapter);
    }

}
