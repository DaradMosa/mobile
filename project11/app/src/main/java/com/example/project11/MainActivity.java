package com.example.project11;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.Package;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.ListView);

        ArrayList<pa> arrayList = new ArrayList<>();
        arrayList.add(new pa(R.drawable.parkguell,"Package1","1500$"));
        arrayList.add(new pa(R.drawable.trevifountain,"Package2","600$"));
        arrayList.add(new pa(R.drawable.museonacional,"Package3","540$"));
        arrayList.add(new pa(R.drawable.parkguell,"Package4","335$"));
        arrayList.add(new pa(R.drawable.trevifountain,"Package5","1000$"));
        arrayList.add(new pa(R.drawable.museonacional,"Package6","730$"));
        arrayList.add(new pa(R.drawable.parkguell,"Package7","560$"));

        PaAdapter paAdapter= new PaAdapter(this,R.layout.list_row,arrayList);
        listView.setAdapter(paAdapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}