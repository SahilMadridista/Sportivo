package com.example.sportivo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1108;
    private static final String TAG ="sportivotag" ;
    private Button continuebutton , googlelogin , facebooklogin;
    private long backpressedtime;
    private Toast backtoast;
    private RelativeLayout appearinglayout;

    GoogleSignInClient mGoogleSignInClient;

    FirebaseAuth mAuth;

    //OnCreate started here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth= FirebaseAuth.getInstance();

        Toast.makeText(MainActivity.this,"Welcome to Sportivo",Toast.LENGTH_SHORT).show();

        if(mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(MainActivity.this, HomePage.class));
        }

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
                signIn();
            }
        });

        //google sign in work starts

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient= GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(MainActivity.this, "Logged In Successfully",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(MainActivity.this, HomePage.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    //google sign in work ends here

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
