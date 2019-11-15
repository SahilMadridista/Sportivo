package com.example.sportivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {
    private DrawerLayout drawer;
    public static FragmentManager fragmentManager;
    private Spinner spinner;

//    HomeBackgroundFragment cricket_fragment;
//    Football_fragment football_fragment;
//    Basketball_Fragment basketball_fragment;
//    Chess_Fragment chess_fragment;
//    Volleyball_Fragment volleyball_fragment;
//    Tennis_Fragment tennis_fragment;
//    Badminton_Fragment badminton_fragment;
//    Hockey_Fragment hockey_fragment;

    FirebaseAuth mAuth;
    private Button signout;

    //OnCreate starts here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        signout = (Button)findViewById(R.id.signout_button);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()==null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }



//        spinner = (Spinner)findViewById(R.id.sportsnamespinner);
//
//        cricket_fragment = new HomeBackgroundFragment();
//        football_fragment = new Football_fragment();
//        basketball_fragment = new Basketball_Fragment();
//        chess_fragment = new Chess_Fragment();
//        volleyball_fragment = new Volleyball_Fragment();
//        tennis_fragment = new Tennis_Fragment();
//        badminton_fragment = new Badminton_Fragment();
//        hockey_fragment = new Hockey_Fragment();
//
//
//        fragmentManager = getSupportFragmentManager();
//
//        if(findViewById(R.id.fragment_container)!=null)
//        {
//            if(savedInstanceState!=null)
//                return;
//            FragmentTransaction ft = fragmentManager.beginTransaction();
//            ft.add(R.id.fragment_container,new HomeBackgroundFragment(),null).commit();
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(HomePage.this,
//                R.layout.custom_spinner,getResources().getStringArray(R.array.sports_names));
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner.setAdapter(adapter);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                switch (i){
//
//                    case 0:
//                        setfragment(cricket_fragment);
//                        Toast.makeText(HomePage.this,"You are in Cricket section",Toast.LENGTH_SHORT)
//                                .show();
//                        break;
//
//                    case 1:
//                        setfragment(football_fragment);
//                        Toast.makeText(HomePage.this,"You are in Football section",Toast.LENGTH_SHORT)
//                                .show();
//                        break;
//
//                    case 2:
//                        setfragment(basketball_fragment);
//                        Toast.makeText(HomePage.this,"You are in Basketball section",Toast.LENGTH_SHORT)
//                                .show();
//                        break;
//
//                    case 3:
//                        setfragment(chess_fragment);
//                        Toast.makeText(HomePage.this,"You are in Chess section",Toast.LENGTH_SHORT)
//                                .show();
//                        break;
//
//                    case 4:
//                        setfragment(volleyball_fragment);
//                        Toast.makeText(HomePage.this,"You are in Volleyball section",Toast.LENGTH_SHORT)
//                                .show();
//                        break;
//
//                    case 5:
//                        setfragment(tennis_fragment);
//                        Toast.makeText(HomePage.this,"You are in Tennis section",Toast.LENGTH_SHORT)
//                                .show();
//                        break;
//
//                    case 6:
//                        setfragment(badminton_fragment);
//                        Toast.makeText(HomePage.this,"You are in Badminton section",Toast.LENGTH_SHORT)
//                                .show();
//                        break;
//
//                    case 7:
//                        setfragment(hockey_fragment);
//                        Toast.makeText(HomePage.this,"You are in Hockey section",Toast.LENGTH_SHORT)
//                                .show();
//                        break;
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        //Drawer thing started
//
//        drawer = (DrawerLayout)findViewById(R.id.drawerlayouthomepage);
//        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(HomePage.this);
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
//                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
//
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        //Drawer thing ended
    }

    //End of OnCreate


    // Switch case for changing the activities for nav drawer started


//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//        switch (menuItem.getItemId()){
//            case R.id.nav_appsettings:
//                Intent appsettings = new Intent(this,AppSettings.class);
//                startActivity(appsettings);
//                break;
//
//            case R.id.nav_profile:
//                Intent profile = new Intent(this,EditProfile.class);
//                startActivity(profile);
//                break;
//
//            case R.id.nav_registration:
//                Intent registration = new Intent(this,YourRegistrations.class);
//                startActivity(registration);
//                break;
//
//            case R.id.nav_savedevents:
//                Intent savedevents = new Intent(this,SavedEvents.class);
//                startActivity(savedevents);
//                break;
//
//            case R.id.nav_hostevent:
//                Intent hostevent = new Intent(this,HostEvent.class);
//                startActivity(hostevent);
//                break;
//
//            case R.id.nav_logout:
//                mAuth.signOut();
//                finish();
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//        }
//
//        return true;
//
//    }

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

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//        String text = adapterView.getItemAtPosition(i).toString();
//        Toast.makeText(adapterView.getContext(),text+ " is selected",Toast.LENGTH_SHORT).show();
//
//    }

//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }

//    public void setfragment(Fragment fragment){
//
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container,fragment);
//        fragmentTransaction.commit();
//
//    }




}

//End of activity