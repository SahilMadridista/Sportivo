package com.example.sportivo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HostEvent extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_event);

        toolbar = findViewById(R.id.toolbarfive);
        toolbar.setTitle("Host Event");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
