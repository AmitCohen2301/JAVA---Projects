package com.example.flightclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FlightChoosing extends AppCompatActivity {
    String originAirport;
    String destAirport;
    String date;
    String itineraryType;
    String classOfService;
    String returnDate;
    TextView fromToText;
    ListView flightListView;
    ArrayList<FlightData> flightsListAsFlights;
    ArrayList<String> flightsListDetails;
    ServerMessager serverMessager = ServerMessager.getInstance(); //Server singleton
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_choosing);


        Intent intent = getIntent();
        originAirport = intent.getStringExtra("originAirport");
        destAirport = intent.getStringExtra("destAirport");
        date = intent.getStringExtra("date");
        itineraryType = intent.getStringExtra("itineraryType");
        classOfService = intent.getStringExtra("classOfService");
        returnDate = intent.getStringExtra("returnDate");


        flightsListAsFlights = new ArrayList<FlightData>();
        flightsListDetails = new ArrayList<String>();

        flightListView = (ListView) findViewById(R.id.flightList);
        fromToText = (TextView) findViewById(R.id.fromToText);
        backButton = (Button) findViewById(R.id.backButton);

        fromToText.setText("From:" + originAirport + "\nTo:" + destAirport + "\nDate:" + date + "\nItinerary type: "
                + itineraryType + "\nClass of service: " + classOfService);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, flightsListDetails);
        flightListView.setAdapter(arrayAdapter);


        flightsListAsFlights.addAll(serverMessager.getFlights(originAirport, destAirport, date , itineraryType , classOfService, returnDate));
        if(flightsListAsFlights.size() == 0){
            flightsListDetails.add("No flights found :(");
        }
        for(FlightData f : flightsListAsFlights){
            flightsListDetails.add(f.toString());
        }
        arrayAdapter.notifyDataSetChanged();

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openAirportChoosing();
            }
        });
    }


    public void openAirportChoosing(){
        Intent intent = new Intent(this, ChoosingAirports.class);
        startActivity(intent);
    }


}