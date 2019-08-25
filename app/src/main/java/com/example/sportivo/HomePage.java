package com.example.sportivo;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , AdapterView.OnItemSelectedListener {

    private DrawerLayout drawer;

    //OnCreate starts here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Spinner started

        Spinner spinner = (Spinner)findViewById(R.id.sportsnamespinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.numbers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        //Spinner ended

        //Drawer thing started


        drawer = (DrawerLayout)findViewById(R.id.drawerlayouthomepage);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Toast.makeText(HomePage.this,"Welcome to Sportivo",Toast.LENGTH_SHORT).show();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Drawer thing ended



    }

    //End of OnCreate



    // Switch case for changing the activities for nav drawer started


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_appsettings:
                Intent appsettings = new Intent(this,AppSettings.class);
                startActivity(appsettings);
                break;

            case R.id.nav_profile:
                Intent profile = new Intent(this,EditProfile.class);
                startActivity(profile);
                break;

            case R.id.nav_registration:
                Intent registration = new Intent(this,YourRegistrations.class);
                startActivity(registration);
                break;

            case R.id.nav_savedevents:
                Intent savedevents = new Intent(this,SavedEvents.class);
                startActivity(savedevents);
                break;

            case R.id.nav_hostevent:
                Intent hostevent = new Intent(this,HostEvent.class);
                startActivity(hostevent);
                break;

        }

        return true;

    }

    // Switch case for changing the activities for nav drawer ended



    //On Back Pressed starts here


    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    //On Back Pressed ends here

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text+ " is selected",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}

//End of activity