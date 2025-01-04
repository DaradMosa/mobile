package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.Arrays;

public class ItineraryDetailsView extends AppCompatActivity {
    String ArrivalDate,DepartureDate,ArrivalTime,DepartureTime,Country,City,Budget;
    ImageButton backBtn;

    TextView txtBudget,txtDest,txtDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_itinerary_details_view);

        txtBudget = findViewById(R.id.txtBudget);
        txtDest = findViewById(R.id.txtDest);
        txtDate = findViewById(R.id.txtDate);
        backBtn = findViewById(R.id.backBtn);
        RecyclerView rvDays = findViewById(R.id.rvDays);
        DaysPaAdapter adapter = new DaysPaAdapter(new ArrayList<>());
        rvDays.setAdapter(adapter);
        rvDays.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        HashMap<String, String> dataMap = (HashMap<String, String>) intent.getSerializableExtra("dataMap");

        // Access the values
        if (dataMap != null) {
             ArrivalDate        = dataMap.get("ArrivalDate");
             DepartureDate      = dataMap.get("DepartureDate");
             ArrivalTime        = dataMap.get("ArrivalTime");
             DepartureTime      = dataMap.get("DepartureTime");
             Country            = dataMap.get("Country");
             City               = dataMap.get("City");
             Budget             = dataMap.get("Budget");
        }

        txtBudget.setText("Budget: "+Budget);
        txtDest.setText(City+", "+Country);
        txtDate.setText(ArrivalDate+" - "+DepartureDate);

//        // Define the itinerary schema
        JSONObject itinerarySchema = new JSONObject();
        try {
            // Define schema properties
            JSONObject schemaProperties = new JSONObject();
            schemaProperties.put("type", "object");
            schemaProperties.put("properties", new JSONObject()
                    .put("estimatedPrice", new JSONObject()
                            .put("type", "number")
                            .put("description", "The estimated total price for the entire trip, including transportation and activities."))
                    .put("days", new JSONObject()
                            .put("type", "object")
                            .put("description", "A dictionary where the keys are day numbers and values are day details.")
                            .put("additionalProperties", new JSONObject()
                                    .put("type", "object")
                                    .put("properties", new JSONObject()
                                            .put("date", new JSONObject()
                                                    .put("type", "string")
                                                    .put("format", "date")
                                                    .put("description", "The date for this day in YYYY-MM-DD format."))
                                            .put("activities", new JSONObject()
                                                    .put("type", "array")
                                                    .put("description", "A list of activities planned for the day.")
                                                    .put("items", new JSONObject()
                                                            .put("type", "object")
                                                            .put("properties", new JSONObject()
                                                                    .put("name", new JSONObject()
                                                                            .put("type", "string")
                                                                            .put("description", "The name of the activity."))
                                                                    .put("time", new JSONObject()
                                                                            .put("type", "string")
                                                                            .put("description", "The time of the activity."))
                                                                    .put("description", new JSONObject()
                                                                            .put("type", "string")
                                                                            .put("description", "A brief description of the activity."))
                                                                    .put("location", new JSONObject()
                                                                            .put("type", "string")
                                                                            .put("description", "The location where the activity takes place."))
                                                                    .put("googleMapsLink", new JSONObject()
                                                                            .put("type", "string")
                                                                            .put("format", "uri")
                                                                            .put("description", "A link to the location on Google Maps."))
                                                                    .put("price", new JSONObject()
                                                                            .put("type", "number")
                                                                            .put("description", "The cost of the activity in USD."))
                                                            )
                                                            .put("required", new JSONArray()
                                                                    .put("name")
                                                                    .put("time")
                                                                    .put("description")
                                                                    .put("location")
                                                                    .put("googleMapsLink")
                                                                    .put("price")
                                                            )
                                                    )
                                            )
                                    )
                                    .put("required", new JSONArray()
                                            .put("date")
                                            .put("activities")
                                    )
                            )
                    )
            );
            schemaProperties.put("required", new JSONArray()
                    .put("estimatedPrice")
                    .put("days")
            );

            itinerarySchema.put("name", "itinerary");
            itinerarySchema.put("strict", false);
            itinerarySchema.put("schema", schemaProperties);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        OpenAIService openAIService = RetrofitClient.getInstance().create(OpenAIService.class);

        // Create the prompt
        String prompt = "Destination: "+City+", "+Country+"\n" +
                "Arrival: "+ArrivalDate+"\n" +
                "Arrival time : "+ArrivalTime+"\n" +
                "Departure: "+DepartureDate+"\n" +
                "Departure time "+DepartureTime+"\n" +
                "Budget: "+Budget+"\n" +
                "Instructions:\n" +
                "Create for 1 person a day-by-day itineraries between the arrival and departure times.\n" +
                "Include at least two (preferably 3) activities per day throughout the day/evening.\n" +
                "Each activity must have:\n" +
                "Name, time, description, location, **a valid** Google Maps link and the estimated price for 1 person in USD.\n" +
                "the link should be in this format https://www.google.com/maps/search/?api=1&query=.\n" +
                "If you cannot generate a valid Google Maps link, leave the field blank.\n" +
                "Give an estimated cost for the trip for 1 person including transportation\n" +
                "Output only valid JSON using the following schema\n"+"{\n" +
                "  \"name\": \"itinerary\",\n" +
                "  \"strict\": false,\n" +
                "  \"schema\": {\n" +
                "    \"type\": \"object\",\n" +
                "    \"properties\": {\n" +
                "      \"estimatedPrice\": {\n" +
                "        \"type\": \"number\",\n" +
                "        \"description\": \"The estimated total price for the entire trip, including transportation and activities.\"\n" +
                "      },\n" +
                "      \"days\": {\n" +
                "        \"type\": \"object\",\n" +
                "        \"description\": \"A dictionary where the keys are day numbers and values are day details.\",\n" +
                "        \"additionalProperties\": {\n" +
                "          \"type\": \"object\",\n" +
                "          \"properties\": {\n" +
                "            \"date\": {\n" +
                "              \"type\": \"string\",\n" +
                "              \"format\": \"date\",\n" +
                "              \"description\": \"The date for this day in YYYY-MM-DD format.\"\n" +
                "            },\n" +
                "            \"activities\": {\n" +
                "              \"type\": \"array\",\n" +
                "              \"description\": \"A list of activities planned for the day.\",\n" +
                "              \"items\": {\n" +
                "                \"type\": \"object\",\n" +
                "                \"properties\": {\n" +
                "                  \"name\": {\n" +
                "                    \"type\": \"string\",\n" +
                "                    \"description\": \"The name of the activity.\"\n" +
                "                  },\n" +
                "                  \"time\": {\n" +
                "                    \"type\": \"string\",\n" +
                "                    \"description\": \"The time of the activity.\"\n" +
                "                  },\n" +
                "                  \"description\": {\n" +
                "                    \"type\": \"string\",\n" +
                "                    \"description\": \"A brief description of the activity.\"\n" +
                "                  },\n" +
                "                  \"location\": {\n" +
                "                    \"type\": \"string\",\n" +
                "                    \"description\": \"The location where the activity takes place.\"\n" +
                "                  },\n" +
                "                  \"googleMapsLink\": {\n" +
                "                    \"type\": \"string\",\n" +
                "                    \"format\": \"uri\",\n" +
                "                    \"description\": \"A link to the location on Google Maps.\"\n" +
                "                  },\n" +
                "                  \"price\": {\n" +
                "                    \"type\": \"number\",\n" +
                "                    \"description\": \"The cost of the activity in USD.\"\n" +
                "                  }\n" +
                "                },\n" +
                "                \"required\": [\n" +
                "                  \"name\",\n" +
                "                  \"time\",\n" +
                "                  \"description\",\n" +
                "                  \"location\",\n" +
                "                  \"googleMapsLink\",\n" +
                "                  \"price\"\n" +
                "                ]\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"required\": [\n" +
                "            \"date\",\n" +
                "            \"activities\"\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"required\": [\n" +
                "      \"estimatedPrice\",\n" +
                "      \"days\"\n" +
                "    ]\n" +
                "  }\n" +
                "}\n";

        // Create request
        OpenAIRequest request = new OpenAIRequest(
                "gpt-4o-2024-08-06",
                Arrays.asList(
                        new OpenAIRequest.Message("system", "You are an itinerary planner."),
                        new OpenAIRequest.Message("user", prompt)
                ),
                3000,
                0.7

        );


        // Make the API call
        Call<OpenAIResponse> call = openAIService.getItinerary(request);
        call.enqueue(new Callback<OpenAIResponse>() {
            @Override
            public void onResponse(Call<OpenAIResponse> call, Response<OpenAIResponse> response) {
                if (response.isSuccessful()) {
                    OpenAIResponse openAIResponse = response.body();
                    if (openAIResponse != null) {
                        // Get the JSON result
                        try {
                            // Extract the JSON object from the response
                            String resp = openAIResponse.getChoices().get(0).getMessage().get("content").toString();
                            Log.d("API Response", resp);

                            JSONObject jsonObject = new JSONObject(resp.substring(resp.indexOf("{"), resp.lastIndexOf("}") + 1));
                            Log.d("Parsed JSON Object", jsonObject.toString());

                            // Access the "days" object
                            JSONObject daysObject = jsonObject.getJSONObject("schema").getJSONObject("days");
                            Log.d("Days JSON Object", daysObject.toString());

                            List<DaysPa> itinerary = new ArrayList<>();

                            // Iterate through the "days" object keys
                            Iterator<String> keys = daysObject.keys();
                            while (keys.hasNext()) {
                                String dayKey = keys.next();
                                JSONObject dayObject = daysObject.getJSONObject(dayKey);

                                // Extract date
                                String date = dayObject.getString("date");

                                // Extract activities
                                JSONArray activitiesArray = dayObject.getJSONArray("activities");
                                List<ItineraryPa> activities = new ArrayList<>();
                                for (int j = 0; j < activitiesArray.length(); j++) {
                                    JSONObject activityObject = activitiesArray.getJSONObject(j);
                                    activities.add(new ItineraryPa(
                                            activityObject.getString("name"),
                                            activityObject.getString("time"),
                                            activityObject.getString("description"),
                                            activityObject.getString("location"),
                                            activityObject.getString("googleMapsLink") // Use the correct key name here
                                    ));
                                }

                                // Create a DaysPa object and add it to the list
                                itinerary.add(new DaysPa(dayKey, date, activities));
                            }

                            // Pass the itinerary list to the RecyclerView
                            runOnUiThread(() -> {
                                adapter.setDays(itinerary);
                                rvDays.setAdapter(adapter);
                            });

                            Log.d("Final Itinerary", itinerary.toString());

                        } catch (JSONException e) {
                            Log.e("JSON Parsing Error", "Error parsing the JSON response", e);
                        }


                    }
                } else {
                    Log.e("API Error", "Response Code: " + response);
                }
            }

            @Override
            public void onFailure(Call<OpenAIResponse> call, Throwable t) {
                Log.e("API Error", t.getMessage());
            }
        });

// Populate data from Firestore
// Use the code from Step 2 to fetch data and pass it to the adapter

//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//        db.collection("itineraries")
//                .document("jU8LvlSoauQ1mCIhZdPG") // Replace with the trip ID
//                .get()
//                .addOnSuccessListener(documentSnapshot -> {
//                    if (documentSnapshot.exists()) {
//                        Map<String, Object> days = (Map<String, Object>) documentSnapshot.get("days");
//                        List<DaysPa> dayList = new ArrayList<>();
//
//                        for (Map.Entry<String, Object> entry : days.entrySet()) {
//                            String dayNumber = entry.getKey();
//                            Map<String, Object> dayDetails = (Map<String, Object>) entry.getValue();
//                            String date = (String) dayDetails.get("date");
//                            List<Map<String, Object>> activitiesData = (List<Map<String, Object>>) dayDetails.get("activities");
//
//                            List<ItineraryPa> activities = new ArrayList<>();
//                            for (Map<String, Object> activityData : activitiesData) {
//                                activities.add(new ItineraryPa(
//                                        (String) activityData.get("name"),
//                                        (String) activityData.get("time"),
//                                        (String) activityData.get("description"),
//                                        (String) activityData.get("location"),
//                                        (String) activityData.get("googleMapsLink")
//                                ));
//                            }
//
//                            dayList.add(new DaysPa(dayNumber, date, activities));
//                        }
//
//                        // Pass the dayList to RecyclerView
//                       // adapter.setDays(dayList);
//                    }
//                })
//                .addOnFailureListener(e -> {
//                    Log.e("Firestore", "Error fetching data", e);
//                });
//
//
//
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