package com.android.carrental;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.android.carrental.model.CreditCard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PaymentMethods extends AppCompatActivity implements View.OnClickListener  {
    private static final int MAX_YEAR = 2099;
    private DatePickerDialog datePickerDialog;
    int exp_month;
    int exp_year;
    Button save_card_details;
    EditText card_number;
    EditText editTextCvv;
    EditText zip_code;
    Button date_selector;
    private Calendar calendar;
    public static final String DATE_FORMAT = "MM yyyy";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);
        save_card_details = (Button)findViewById(R.id.save_card_details);
        card_number = (EditText)findViewById(R.id.card_number);
        editTextCvv = (EditText)findViewById(R.id.cvv);
        zip_code = (EditText)findViewById(R.id.zip_code);
        save_card_details.setOnClickListener(this);
        getSupportActionBar().setTitle("Edit Payment Method");
        date_selector = (Button)findViewById(R.id.date_selector);
        calendar = Calendar.getInstance();
        date_selector.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.save_card_details : validateDetails();break;
            case R.id.date_selector : selectDate();break;

        }
    }
    private void validateDetails(){
        String cardNumber = card_number.getText().toString().trim();
        String cvv = editTextCvv.getText().toString().trim();
        String zipCode = zip_code.getText().toString().trim();
        if (cardNumber.isEmpty()) {
            card_number.setError(getString(R.string.input_error));
            card_number.requestFocus();
            return;
        }
        if (cvv.isEmpty()) {
            editTextCvv.setError(getString(R.string.input_error));
            editTextCvv.requestFocus();
            return;
        }
        if (zipCode.isEmpty()) {
            zip_code.setError(getString(R.string.input_error));
            zip_code.requestFocus();
            return;
        }
        if(exp_month == 0) exp_month = 1;
        if(exp_year ==0) exp_year = 2019;
        CreditCard card = new CreditCard(cardNumber,cvv,zipCode,exp_month+"",exp_year+"");
        FirebaseDatabase.getInstance().getReference("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("PaymentCards")
                .setValue(card).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Saved Successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
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
}
