package com.example.sportivo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVerificationActivity extends AppCompatActivity {
    String phone;
    FirebaseAuth mAuth;
    String codeSentToUser;
    EditText otpcode;
    Button verify;
    private ProgressBar progressBar;
    FirebaseAuthSettings firebaseAuthSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar)findViewById(R.id.progressbar);

        phone = getIntent().getExtras().getString("phonenumber");
        sendVerificationCode(phone); //Method to send otp

        //.......... Configure faking the auto-retrieval with the whitelisted numbers.
        firebaseAuthSettings = mAuth.getFirebaseAuthSettings();
        firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phone, codeSentToUser);


        otpcode=findViewById(R.id.otp);
        verify= (Button) findViewById(R.id.otpverify);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code= otpcode.getText().toString().trim();
                if(code.length()!=6)
                {
                    otpcode.setError("Error Code");
                    otpcode.requestFocus();
                    return;
                }
                verifyOTPcode(code);
            }
        });
    }

    //to check if the user is logged in, then directly go to the homepage
    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(OtpVerificationActivity.this, HomePage.class));
        }

    }
    //checking of user if logged in or not ended here

    //verifying of the OTP sent to the user
    private void verifyOTPcode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSentToUser , code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
             if (task.isSuccessful()) {
                 Toast.makeText(OtpVerificationActivity.this, "Logged In Successfully",Toast.LENGTH_SHORT).show();
                 finish();
                 startActivity(new Intent(OtpVerificationActivity.this, HomePage.class));
                 }
                 else {
                     if( task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                         Toast.makeText(OtpVerificationActivity.this, "Incorrect Verification Code",Toast.LENGTH_SHORT).show();
                     }
                 }
           }
         });
    }

    //Verification of OTP code completed here

    //Sending the verification code to the user
    private void sendVerificationCode(String phone)
    {
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                100,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacksPhoneAuthActivity.java
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks= new PhoneAuthProvider
            .OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code!=null) {
                otpcode.setText(code);
                verifyOTPcode(code);
            }
        }
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSentToUser =  s;                   //OTP that is sent to the user
        }
        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OtpVerificationActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    };


}
