package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItineraryDetailsView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_itinerary_details_view);


        RecyclerView rvDays = findViewById(R.id.rvDays);
        DaysPaAdapter adapter = new DaysPaAdapter(new ArrayList<>());
        rvDays.setAdapter(adapter);
        rvDays.setLayoutManager(new LinearLayoutManager(this));

// Populate data from Firestore
// Use the code from Step 2 to fetch data and pass it to the adapter

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("itineraries")
                .document("jU8LvlSoauQ1mCIhZdPG") // Replace with the trip ID
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Map<String, Object> days = (Map<String, Object>) documentSnapshot.get("days");
                        List<DaysPa> dayList = new ArrayList<>();

                        for (Map.Entry<String, Object> entry : days.entrySet()) {
                            String dayNumber = entry.getKey();
                            Map<String, Object> dayDetails = (Map<String, Object>) entry.getValue();
                            String date = (String) dayDetails.get("date");
                            List<Map<String, Object>> activitiesData = (List<Map<String, Object>>) dayDetails.get("activities");

                            List<ItineraryPa> activities = new ArrayList<>();
                            for (Map<String, Object> activityData : activitiesData) {
                                activities.add(new ItineraryPa(
                                        (String) activityData.get("name"),
                                        (String) activityData.get("time"),
                                        (String) activityData.get("description"),
                                        (String) activityData.get("location"),
                                        (String) activityData.get("googleMapsLink")
                                ));
                            }

                            dayList.add(new DaysPa(dayNumber, date, activities));
                        }

                        // Pass the dayList to RecyclerView
                        adapter.setDays(dayList);
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Error fetching data", e);
                });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}