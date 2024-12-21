package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.Package;
import java.util.ArrayList;

public class ItineraryView extends AppCompatActivity {

    ListView listView;
    ImageButton backBtn;

    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_itinerary_view);

        listView        = findViewById(R.id.ListView);
        backBtn         = findViewById(R.id.backBtn);

        ArrayList<pa> arrayList = new ArrayList<>();
        arrayList.add(new pa(R.drawable.trevifountain,"Package1","1500$"));
        arrayList.add(new pa(R.drawable.trevifountain,"Package2","600$"));
        arrayList.add(new pa(R.drawable.trevifountain,"Package3","540$"));
        arrayList.add(new pa(R.drawable.trevifountain,"Package4","335$"));
        arrayList.add(new pa(R.drawable.trevifountain,"Package5","1000$"));
        arrayList.add(new pa(R.drawable.trevifountain,"Package6","730$"));
        arrayList.add(new pa(R.drawable.trevifountain,"Package7","560$"));

        PaAdapter paAdapter= new PaAdapter(this,R.layout.list_row,arrayList);
        listView.setAdapter(paAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            //String clickedItem = (String) parent.getItemAtPosition(position);
            startActivity(new Intent(ItineraryView.this, ItineraryDetailsView.class));

        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}