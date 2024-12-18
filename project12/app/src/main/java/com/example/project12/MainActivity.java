package com.example.project12;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {
     private String selectedCountry,selectedCity;
     private TextView tvcountryS,tvCityS;
     private Spinner countrySp,citySp;
     private ArrayAdapter<CharSequence> countryAdapter;
    private ArrayAdapter<CharSequence> cityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        countrySp=findViewById(R.id.spincountry);

        countryAdapter=ArrayAdapter.createFromResource(this,R.array.spincountry,R.layout.spinner_layout);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySp.setAdapter(countryAdapter);
        countrySp.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                citySp=findViewById(R.id.spincity);
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}