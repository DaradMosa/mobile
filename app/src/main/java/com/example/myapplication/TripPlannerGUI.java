package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.io.Serializable;
import java.util.Map;


public class TripPlannerGUI extends AppCompatActivity {

        private String selectedCountry,selectedCity;
        private TextView tvcountryS,tvCityS;
        private Spinner countrySp,citySp;
        private ArrayAdapter<CharSequence> countryAdapter;
        private ArrayAdapter<CharSequence> cityAdapter;
        private EditText depEd1,budgetTxt;
        final Calendar depCal= Calendar.getInstance();
        final Calendar retCal= Calendar.getInstance();
        private DatePickerDialog dpd;
        private EditText retEd1;
        private Button submitBtn;
        private ImageButton backBtn;
        private EditText timePickerEditText,retTimePicker;
        private String selectedArrivalTime,selectedArrivalDate
                ,selectedDepartureTime,selectedDepartureDate;
        public HashMap<String, String> dataMap;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_trip_planner_gui);
            countrySp=findViewById(R.id.spincountry);

            countryAdapter          = ArrayAdapter.createFromResource(this,R.array.spincountry,R.layout.spinner_layout);
            countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            countrySp.setAdapter(countryAdapter);
            depEd1                  = findViewById(R.id.depcal);
            retEd1                  = findViewById(R.id.retcal);
            backBtn                 = findViewById(R.id.backBtn);
            submitBtn               = findViewById(R.id.submit);
            timePickerEditText      = findViewById(R.id.timePickerEditText);
            retTimePicker           = findViewById(R.id.retTimePicker);
            citySp                  = findViewById(R.id.spincity);
            budgetTxt               = findViewById(R.id.budgetTxt);

            dataMap                 = new HashMap<>();
            depEd1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(TripPlannerGUI.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            depCal.set(Calendar.YEAR,year);
                            depCal.set(Calendar.MONTH,month);
                            depCal.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                            selectedArrivalDate="dd-MM-yyyy";
                            SimpleDateFormat dateFormat=new SimpleDateFormat(selectedArrivalDate, Locale.US);
                            depEd1.setText(dateFormat.format(depCal.getTime()));
                            dataMap.put("ArrivalDate", dateFormat.format(depCal.getTime()));
                        }
                    }, depCal.get(Calendar.YEAR), depCal.get(Calendar.MONTH), depCal.get(Calendar.DAY_OF_MONTH)).show();
                }
            });
            retEd1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(TripPlannerGUI.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            retCal.set(Calendar.YEAR,year);
                            retCal.set(Calendar.MONTH,month);
                            retCal.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                            selectedDepartureDate="dd-MM-yyyy";
                            SimpleDateFormat dateFormat=new SimpleDateFormat(selectedDepartureDate, Locale.US);
                            retEd1.setText(dateFormat.format(retCal.getTime()));
                            dataMap.put("DepartureDate", dateFormat.format(retCal.getTime()));

                        }
                    }, retCal.get(Calendar.YEAR), retCal.get(Calendar.MONTH), retCal.get(Calendar.DAY_OF_MONTH)).show();
                }
            });




            timePickerEditText.setOnClickListener(v -> {
                // Get the current time
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                // Show the TimePickerDialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        TripPlannerGUI.this,
                        (TimePicker view, int selectedHour, int selectedMinute) -> {
                            // Update the EditText with the selected time
                            selectedArrivalTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                            timePickerEditText.setText(selectedArrivalTime);
                            dataMap.put("ArrivalTime",selectedArrivalTime);

                        },
                        hour,
                        minute,
                        true // Set to true for 24-hour format, false for 12-hour format
                );

                timePickerDialog.show();
            });

            retTimePicker.setOnClickListener(v -> {
                // Get the current time
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                // Show the TimePickerDialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        TripPlannerGUI.this,
                        (TimePicker view, int selectedHour, int selectedMinute) -> {
                            // Update the EditText with the selected time
                            selectedDepartureTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                            retTimePicker.setText(selectedDepartureTime);
                            dataMap.put("DepartureTime",selectedDepartureTime);
                        },
                        hour,
                        minute,
                        true // Set to true for 24-hour format, false for 12-hour format
                );

                timePickerDialog.show();
            });

            countrySp.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedCountry= countrySp.getSelectedItem().toString();
                    int parentID = parent.getId();
                    if(parentID==R.id.spincountry){
                        switch(selectedCountry){
                            case "Select the Country": cityAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_spinCity,R.layout.spinner_layout );
                                break;
                            case"France":cityAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_france_cities,R.layout.spinner_layout );break;
                            case"Italy":cityAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_italy_cities,R.layout.spinner_layout ); break;
                            case"Turkey":cityAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_turkey_cities,R.layout.spinner_layout );break;
                            case "Germany":cityAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_germany_cities,R.layout.spinner_layout );break;
                            case"Russia":cityAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_russia_cities,R.layout.spinner_layout );break;
                            case"Jordan":cityAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_jordan_cities,R.layout.spinner_layout );break;
                            case "Palestine":cityAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_palestine_cities,R.layout.spinner_layout );break;
                            case"Iraq":cityAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_iraq_cities,R.layout.spinner_layout );break;
                            case"Lebanon":cityAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_lebanon_cities,R.layout.spinner_layout );break;
                            case"Usa":cityAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_usa_cities,R.layout.spinner_layout );break;
                            case"Canada":cityAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_canada_cities,R.layout.spinner_layout );break;
                            case"Mexico":cityAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.arr_mexico_cities,R.layout.spinner_layout );break;
                            default:break;

                        }
                        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        citySp.setAdapter(cityAdapter);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            }) ;

            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataMap.put("Country",countrySp.getSelectedItem().toString());
                    dataMap.put("City",citySp.getSelectedItem().toString());
                    dataMap.put("Budget",budgetTxt.getText().toString());
                    Intent intent = new Intent(TripPlannerGUI.this, ItineraryDetailsView.class);
                    intent.putExtra("dataMap", dataMap);
                    startActivity(intent);
                }
            });

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

        }

        private void validateFields()
        {
            if (countrySp.getSelectedItem().toString().equals("Select the Country")) {
                Toast.makeText(this, "Please select a country!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(selectedDepartureTime)) {
                retTimePicker.setError("Select your departure time!");
                return;
            }
            if (TextUtils.isEmpty(selectedArrivalTime)) {
                timePickerEditText.setError("Select your arrival time!");
                return;
            }

        }
}