package com.example.sportivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.dynamic.OnDelegateCreatedListener;

public class PhoneAuthActivity extends AppCompatActivity implements View.OnClickListener {
    EditText countrycode;
    EditText phonenumber;
    Button submit;
    String userphonenumber, code, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);

        countrycode = findViewById(R.id.countrycode);
        phonenumber = findViewById(R.id.phonenumber);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(this);
    }

    //onClick methos starts here
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.submit) {

            code = countrycode.getText().toString().trim();
            if(code.isEmpty()){
                code="+91";
            }

            number = phonenumber.getText().toString().trim();
            if (number.length()!=10){
                phonenumber.setError("Enter a valid number");
                phonenumber.requestFocus();
                return;
            }

            userphonenumber = code + number;

            Intent intent = new Intent(PhoneAuthActivity.this, OtpVerificationActivity.class);
            intent.putExtra("phonenumber", userphonenumber);
            startActivity(intent);
        }

    }
}