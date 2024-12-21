package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

    private FirebaseAuth             mAuth;
    private FirebaseFirestore        firestore;
    private TextView                 txtUserName;
    private UserViewModel            userViewModel;
    private Button                   btnPlanYourTrip;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_page);

        // Initialize Firebase Auth and Firestore
        mAuth                = FirebaseAuth.getInstance();
        firestore            = FirebaseFirestore.getInstance();

        userViewModel        = new ViewModelProvider(this).get(UserViewModel.class);

        txtUserName          = findViewById(R.id.txtUserName);
        btnPlanYourTrip      = findViewById(R.id.btnPlanYourTrip);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                //startActivity(new Intent(this, mainPage.class));
                return true;
            } else if (item.getItemId() == R.id.nav_profile) {
                startActivity(new Intent(this, profilePage.class));
                return true;
            }
            else if (item.getItemId() == R.id.nav_map) {
                startActivity(new Intent(this, MapUI.class));
                return true;
            }

            return false;
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.frontpage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnPlanYourTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainPage.this, TripPlannerGUI.class));
            }
        });
    }

    public void onStart() {

        super.onStart();
        //Toast.makeText(mainPage.this, "Registration Failed: "+ FirebaseAuth.getInstance().getCurrentUser(), Toast.LENGTH_SHORT).show();

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {

            startActivity(new Intent(mainPage.this, signIn.class));
            finish();
        }

        userViewModel    = new ViewModelProvider(this).get(UserViewModel.class);
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        userViewModel.setUsername(sharedPreferences.getString("username", "Default Username"));
        userViewModel.setEmail(sharedPreferences.getString("email", "Default Email"));

        txtUserName.setText(userViewModel.getUsername());



    }

    public void onResume(){

        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

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