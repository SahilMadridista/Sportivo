package com.example.sportivo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SavedEvents extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_events);

        toolbar = findViewById(R.id.toolbarfour);
        toolbar.setTitle("Saved Events");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
