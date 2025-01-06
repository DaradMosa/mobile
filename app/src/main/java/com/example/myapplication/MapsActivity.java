package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Initialize the map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Sample package itinerary
        List<ItineraryItem> itinerary = new ArrayList<>();
        itinerary.add(new ItineraryItem("1. Morning - San Francisco", new LatLng(37.7749, -122.4194))); // San Francisco
        itinerary.add(new ItineraryItem("2. Afternoon - Los Angeles", new LatLng(34.0522, -118.2437))); // Los Angeles
        itinerary.add(new ItineraryItem("3. Evening - Las Vegas", new LatLng(36.1699, -115.1398))); // Las Vegas

        // Add markers to the map
        for (int i = 0; i < itinerary.size(); i++) {
            ItineraryItem item = itinerary.get(i);
            mMap.addMarker(new MarkerOptions()
                    .position(item.getLocation())
                    .title(item.getName()));
        }

        // Move the camera to the first marker
        if (!itinerary.isEmpty()) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(itinerary.get(0).getLocation(), 5));
        }
    }

    // Helper class for itinerary items
    static class ItineraryItem {
        private final String name;
        private final LatLng location;

        public ItineraryItem(String name, LatLng location) {
            this.name = name;
            this.location = location;
        }

        public String getName() {
            return name;
        }

        public LatLng getLocation() {
            return location;
        }
    }
}
