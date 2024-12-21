package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.atomic.AtomicReference;

public class mainPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private TextView txtUserName;
    private UserViewModel userViewModel;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_page);

        // Initialize Firebase Auth and Firestore
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        txtUserName = findViewById(R.id.txtUserName);



        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                startActivity(new Intent(this, mainPage.class));
                return true;
            } else if (item.getItemId() == R.id.nav_profile) {
                startActivity(new Intent(this, profilePage.class));
                return true;
            }
            return false;
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.frontpage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onStart() {

        super.onStart();
        //Toast.makeText(mainPage.this, "Registration Failed: "+ FirebaseAuth.getInstance().getCurrentUser(), Toast.LENGTH_SHORT).show();

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {

            startActivity(new Intent(mainPage.this, signIn.class));
            finish();
        }
//        userViewModel.setUsername(fetchUsername());
//        txtUserName.setText(userViewModel.getUsername());
    }

    private  String fetchUsername() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        AtomicReference<String> username = null;
        if (currentUser != null) {
            String userId = currentUser.getUid(); // Get the UID of the logged-in user

            // Retrieve the user's document from Firestore
            firestore.collection("Users").document(userId)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful() && task.getResult() != null) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // Retrieve the username field
                                username.set(document.getString("username"));


                            } else {
                                Log.d("error", "User data does not exist");
                            }
                        } else {
                            Log.d("error", "User data does not exist");
                        }
                    });
        } else {
            Log.d("error", "User data does not exist");
        }
        return username.get();
    }
}