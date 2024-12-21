package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapUI extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private RecyclerView recyclerView;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_ui);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }


        mMap.setMyLocationEnabled(true);
        LatLng defaultLocation = new LatLng(31.963158, 35.930359);
        mMap.addMarker(new MarkerOptions().position(defaultLocation).title("Marker in Amman"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12f));


        List<String> places = new ArrayList<>();
        places.add("Park");
        places.add("Museum");
        places.add("Restaurant");
        places.add("Shopping Mall");


        int freeHours = 8;
        int tripDays = 2;
        Map<String, List<String>> multiDayItinerary = createMultiDayItinerary(places, freeHours, tripDays);
        displayMultiDayItinerary(multiDayItinerary);

        double transportCost = 50.0;
        double foodCostPerPlace = 20.0;
        double entryFees = 30.0;
        double totalCost = calculateBudget(transportCost, foodCostPerPlace, entryFees, places.size());
        showBudgetSummary(totalCost);
    }

    private Map<String, List<String>> createMultiDayItinerary(List<String> places, int freeHours, int days) {
        Map<String, List<String>> itineraryByDay = new LinkedHashMap<>();
        int placesPerDay = (int) Math.ceil((double) places.size() / days);
        int timePerPlace = freeHours * 60 / places.size();

        for (int day = 1; day <= days; day++) {
            List<String> dailyItinerary = new ArrayList<>();
            for (int i = 0; i < placesPerDay && !places.isEmpty(); i++) {
                String place = places.remove(0);
                String weather = getMockWeather(place);
                dailyItinerary.add(place + " - " + weather + " - " + timePerPlace + " mins");
            }
            itineraryByDay.put("Day " + day, dailyItinerary);
        }
        return itineraryByDay;
    }

    private String getMockWeather(String place) {
        Map<String, String> weatherData = new LinkedHashMap<>();
        weatherData.put("Park", "Sunny, 25°C");
        weatherData.put("Museum", "Cloudy, 20°C");
        weatherData.put("Restaurant", "Rainy, 18°C");
        weatherData.put("Shopping Mall", "Clear, 22°C");
        return weatherData.getOrDefault(place, "Unknown Weather");
    }

    private void displayMultiDayItinerary(Map<String, List<String>> itinerary) {
        List<String> combinedItinerary = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : itinerary.entrySet()) {
            combinedItinerary.add(entry.getKey());
            combinedItinerary.addAll(entry.getValue());
        }
        MapsAdapter adapter = new MapsAdapter(combinedItinerary);
        recyclerView.setAdapter(adapter);
    }

    private double calculateBudget(double transportCost, double foodCost, double entryFees, int places) {
        return transportCost + (foodCost * places) + entryFees;
    }

    private void showBudgetSummary(double totalCost) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Budget Summary")
                .setMessage("Estimated Trip Cost: $" + totalCost)
                .setPositiveButton("OK", null)
                .show();
    }
}