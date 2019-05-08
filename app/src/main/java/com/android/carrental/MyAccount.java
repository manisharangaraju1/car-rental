package com.android.carrental;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.carrental.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyAccount extends AppCompatActivity implements View.OnClickListener {
    TextView textViewName;
    TextView textViewEmail;
    TextView textViewPhoneNumber;
    TextView textViewStreetAddress;
    TextView textViewAptNumber;
    TextView textViewCity;
    TextView textViewZipCode;
    Button editDetails;
    Intent intent;
    String name;
    String email;
    String phoneNumber;
    String streetAddress;
    String aptNumber;
    String city;
    String zipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        textViewName = (TextView)findViewById(R.id.name);
        textViewEmail = (TextView)findViewById(R.id.email);
        textViewPhoneNumber = (TextView)findViewById(R.id.phone_number);
        textViewStreetAddress = (TextView)findViewById(R.id.street_address);
        textViewAptNumber = (TextView)findViewById(R.id.apt_number);
        textViewCity = (TextView)findViewById(R.id.city);
        textViewZipCode = (TextView)findViewById(R.id.zip_code);
        editDetails = (Button)findViewById(R.id.edit_details);
        intent = new Intent(MyAccount.this,EditMyAccount.class);
        editDetails.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        final String uid =  FirebaseAuth.getInstance().getUid();
        Log.i("UID!",uid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot users : dataSnapshot.getChildren()){
                    User user = users.getValue(User.class);
                    if(user.getId().equals(uid)){
                        name = user.getName();
                        email = user.getEmail();
                        phoneNumber =user.getPhoneNumber();
                        streetAddress = user.getStreetAddress();
                        aptNumber = user.getAptNumber();
                        city =user.getCity();
                        zipCode = user.getZipCode();
                        textViewName.setText(name);
                        textViewEmail.setText(email);
                        textViewPhoneNumber.setText(phoneNumber);
                        textViewStreetAddress.setText(streetAddress);
                        textViewAptNumber.setText(aptNumber);
                        textViewCity.setText(city);
                        textViewZipCode.setText(zipCode);

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.edit_details){
            Bundle userDetails = new Bundle();
            userDetails.putString("name",name);
            userDetails.putString("email",email);
            userDetails.putString("phoneNumber",phoneNumber);
            userDetails.putString("streetAddress",streetAddress);
            userDetails.putString("aptNumber",aptNumber);
            userDetails.putString("city",city);
            userDetails.putString("zipCode",zipCode);
            intent.putExtra("userDetails",userDetails);
            startActivity(intent);
        }
    }
}
