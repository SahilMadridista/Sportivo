package com.example.sportivo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button continuebutton , googlelogin , facebooklogin;
    private long backpressedtime;
    private Toast backtoast;
    private RelativeLayout appearinglayout;


    //OnCreate started here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this,"Welcome to Sportivo",Toast.LENGTH_SHORT).show();

        continuebutton = (Button) findViewById(R.id.continuebutton);

        googlelogin = (Button)findViewById(R.id.googleloginbutton);
        facebooklogin = (Button)findViewById(R.id.facebookloginbutton);

        appearinglayout = (RelativeLayout)findViewById(R.id.googleandfbrelativelayout);

        continuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appearloginlayout();
            }
        });

        googlelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });

    }

    //OnCreate ended here

    //Homepage opening method started

    public void openHomePage(){
        Intent intent = new Intent(MainActivity.this,HomePage.class);
        startActivity(intent);
    }

    //Homepage opening method ended



    //login layout appearance start

    public void appearloginlayout(){
        appearinglayout.animate().alpha(1).setDuration(200);
    }

    //slide layout appearance ends




    //Backpressed started here

    @Override
    public void onBackPressed() {
        if(backpressedtime+2000>System.currentTimeMillis()){
            backtoast.cancel();
            super.onBackPressed();
            return;
        }
        else {
            backtoast= Toast.makeText(MainActivity.this,"Press back again to exit",Toast.
                    LENGTH_SHORT);
            backtoast.show();

        }
        backpressedtime = System.currentTimeMillis();
    }

    //Backpressed ended here


}
