package com.example.sportivo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AppSettings extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);

        toolbar = findViewById(R.id.toolbarsix);
        toolbar.setTitle("Your Profile");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

}
