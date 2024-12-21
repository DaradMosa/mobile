package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class profilePage extends AppCompatActivity {

    private Button btnSignOut;
    private BottomNavigationView bottomNavigationView;
    private UserViewModel userViewModel;
    private TextView nameofusr,txtUserEmail;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_page);

        userViewModel    = new ViewModelProvider(this).get(UserViewModel.class);
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        userViewModel.setUsername(sharedPreferences.getString("username", "Default Username"));
        userViewModel.setEmail(sharedPreferences.getString("email", "Default Email"));

        btnSignOut       = findViewById(R.id.btnSignOut);
        backBtn          = findViewById(R.id.backBtn);
        nameofusr        = findViewById(R.id.nameofusr);
        txtUserEmail     = findViewById(R.id.txtUserEmail);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nameofusr.setText(userViewModel.getUsername());
        txtUserEmail.setText(userViewModel.getEmail());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                startActivity(new Intent(this, mainPage.class));
                return true;
            } else if (item.getItemId() == R.id.nav_profile) {
                //startActivity(new Intent(this, profilePage.class));
                return true;
            }
            return false;
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(profilePage.this, signIn.class));

            }
        });

    }
    public void onResume(){

        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);

    }
}