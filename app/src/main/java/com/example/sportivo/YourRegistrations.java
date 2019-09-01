package com.example.sportivo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class YourRegistrations extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_registrations);

        toolbar = findViewById(R.id.toolbarthree);
        toolbar.setTitle("Your Registrations");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
