package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signIn extends AppCompatActivity {

    private TextView           signInEmail;
    private  TextView          signInPass;
    private Button             signinBtn;
    private TextView           signupRedirectText;
    private FirebaseAuth       mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);


        mAuth = FirebaseAuth.getInstance();
        signInEmail = findViewById(R.id.signinEmail);
        signInPass = findViewById(R.id.signinpass);
        signinBtn = findViewById(R.id.signinbtn);
        signupRedirectText = findViewById(R.id.textView4);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Handle Sign-In Button Click
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

            }
        });

        // Redirect to Sign-Up Page
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signIn.this, activity_sign_up.class));
            }
        });
    }

    public void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(signIn.this, mainPage.class));
            finish();
        }
    }
    private void loginUser() {
        String email        = signInEmail.getText().toString().trim();
        String password     = signInPass.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            signInEmail.setError("Email is required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            signInPass.setError("Password is required");
            return;
        }

        // Sign in the user with Firebase Auth
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        Toast.makeText(signIn.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signIn.this, mainPage.class)); // Redirect to main activity
                        finish();
                    } else {
                        Toast.makeText(signIn.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}