package com.example.flightclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ChoosingAirports extends AppCompatActivity {
    TextView flightMainText;
    TextView date;
    ListView airportListView;
    EditText countrySelector;
    ArrayList<String> airportList;
    Button confirmButton;
    TextView originChoiceView;
    ServerMessager serverMessager = ServerMessager.getInstance(); //Server singleton
    Boolean originSelected = false;
    TextView selectedOriginView;
    TextView selectedDestView;
    String originAirportName;
    String destAirportName;
    Boolean dateCorrect = false;
    TextView dateViewer;
    ImageButton calendarButton;
    CalendarView calendar;
    String dateStr = "";
    String returnDateStr = "";
    boolean visibleCalendarView = false;
    RadioGroup tripTypeRadioGroup;

    RadioGroup classTypeRadioGroup;
    String flightItinerary = "";
    String flightClass = "";
    TextView itinenaryTypeText;
    TextView classOfService;
    //radios
    RadioButton roundedRadio;
    RadioButton oneWayRadio;
    RadioButton firstRadio;
    RadioButton economyRadio;
    RadioButton premEconomyRadio;
    RadioButton businessRadio;
    ArrayList<RadioButton> allRadioButtons;
    TextView chooseAirportText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_airports);
        flightMainText = (TextView) findViewById(R.id.flightMainText);
        airportListView = (ListView) findViewById(R.id.airportsListView);
        countrySelector = (EditText) findViewById(R.id.countryChoiceInput);
        confirmButton = (Button) findViewById(R.id.doneSearch);
        originChoiceView = (TextView) findViewById(R.id.originChoiceView);
        selectedOriginView = (TextView)  findViewById(R.id.selectedOrigin);
        selectedDestView = (TextView)  findViewById(R.id.selectedDest);
        dateViewer = (TextView)  findViewById(R.id.dateViewer);
        calendarButton = (ImageButton) findViewById(R.id.calendarButton);
        calendar = (CalendarView) findViewById(R.id.calendarView);
        itinenaryTypeText = (TextView) findViewById(R.id.select_triptype);
        classOfService = (TextView) findViewById(R.id.select_classtype);
        chooseAirportText = (TextView) findViewById(R.id.text_choose_AP);

        //radio
        tripTypeRadioGroup = (RadioGroup) findViewById(R.id.tripRadioGroup);
        classTypeRadioGroup = (RadioGroup) findViewById(R.id.classRadioGroup);

        //buttons radio
        roundedRadio = (RadioButton)  findViewById(R.id.radio_rounded);
        oneWayRadio = (RadioButton)  findViewById(R.id.radio_oneway);
        businessRadio = (RadioButton)  findViewById(R.id.radio_business);
        firstRadio = (RadioButton)  findViewById(R.id.radio_first);
        premEconomyRadio = (RadioButton)  findViewById(R.id.radio_premium);
        economyRadio = (RadioButton)  findViewById(R.id.radio_economy);
        allRadioButtons = new ArrayList<RadioButton>();
        allRadioButtons.addAll(Arrays.asList(roundedRadio, oneWayRadio, businessRadio, firstRadio, premEconomyRadio, economyRadio));






        selectedDestView.setVisibility(View.GONE);
        selectedOriginView.setVisibility(View.GONE);
        dateViewer.setVisibility(View.GONE);
        calendar.setVisibility(View.GONE);



        confirmButton.setClickable(false);
        confirmButton.setBackgroundColor(Color.GRAY);

        //Setting up array list

        airportList = new ArrayList<String>();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, airportList);
        airportListView.setAdapter(arrayAdapter);

        //for search button---------------------------
        countrySelector.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                confirmButton.setClickable(false);
                confirmButton.setBackgroundColor(Color.GRAY);
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(countrySelector.getText().toString().equals(""))
                {
                    confirmButton.setClickable(false);
                    confirmButton.setBackgroundColor(Color.GRAY);
                }else {
                    if(dateCorrect && tripTypeRadioGroup.getCheckedRadioButtonId() != -1 && classTypeRadioGroup.getCheckedRadioButtonId() != -1 && flightClass != "" && flightItinerary != ""){
                        confirmButton.setClickable(true);
                        confirmButton.setBackgroundColor(Color.BLUE);
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //-----------------------------------------------

        //Button searched--------------------------------
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(countrySelector.getWindowToken(), 0);
                System.out.println("first done clicked");

                // to access server
                // pull all info and add it to list
                ArrayList<String> serverValues = serverMessager.getAirportsByCountry(countrySelector.getText().toString()); // gets values from server
                if(serverValues.size() != 0){ // if airport and country found
                    airportList.clear();
                    calendarButton.setVisibility(View.GONE);

                    itinenaryTypeText.setVisibility(View.GONE);
                    classOfService.setVisibility(View.GONE);

                    classTypeRadioGroup.setVisibility(View.GONE);
                    tripTypeRadioGroup.setVisibility(View.GONE);

                    airportList.addAll(serverValues);
                    arrayAdapter.notifyDataSetChanged();
                    flightMainText.setText("Here are your flights");

                }else{
                    airportList.clear();
                    flightMainText.setText("No results found please try again");

                    arrayAdapter.notifyDataSetChanged();
                }
                airportListView.setVisibility(View.VISIBLE);
                chooseAirportText.setVisibility(View.VISIBLE);
            }
        });

        //List view element click----------------------------

        airportListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Selected item at position:" + position + "->" + airportList.get(position));
                if(!originSelected) {

                        originSelected = true;
                        originAirportName = airportList.get(position);
                        originChoiceView.setText("Choose destination country:");
                        selectedOriginView.setVisibility(View.VISIBLE);
                        selectedOriginView.setText(selectedOriginView.getText().toString() + " " + airportList.get(position));
                        //empty out every thing
                        airportList.clear();
                        chooseAirportText.setVisibility(View.GONE);
                        arrayAdapter.notifyDataSetChanged();
                        countrySelector.setText("");

                }else{

                    destAirportName = airportList.get(position);
//                    selectedDestView.setVisibility(View.VISIBLE);
//                    selectedDestView.setText(selectedDestView.getText().toString() + " " + airportList.get(position));
//                    airportListView.setVisibility(View.GONE);
//
//
//                    //Set everything invisible
//                    airportListView.setVisibility(View.GONE);
//                    countrySelector.setVisibility(View.GONE);
//                    confirmButton.setVisibility(View.GONE);
//                    originChoiceView.setVisibility(View.GONE);
//
//                    flightMainText.setText("Searching flights...");
//                    flightMainText.setTextColor(Color.BLUE);
                    openFlightsChoosing();


                }
            }
        });
        //----------------------------------------------------
        calendarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!visibleCalendarView) {
                    calendar.setVisibility(View.VISIBLE);
                    visibleCalendarView = true;
                    tripTypeRadioGroup.setVisibility(View.GONE);
                    classTypeRadioGroup.setVisibility(View.GONE);
                    itinenaryTypeText.setVisibility(View.GONE);
                    classOfService.setVisibility(View.GONE);
                }
                else {
                    calendar.setVisibility(View.GONE);
                    visibleCalendarView = false;
                    tripTypeRadioGroup.setVisibility(View.VISIBLE);
                    classTypeRadioGroup.setVisibility(View.VISIBLE);
                    itinenaryTypeText.setVisibility(View.VISIBLE);
                    classOfService.setVisibility(View.VISIBLE);
                }
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(countrySelector.getWindowToken(), 0);


            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                String newDateStr = String.valueOf(year) + "-";
                if(month < 9)
                    newDateStr += "0" + String.valueOf(month);
                else
                    newDateStr += String.valueOf(month);
                newDateStr += "-";
                if(dayOfMonth < 9)
                    newDateStr += "0" + String.valueOf(dayOfMonth);
                else
                    newDateStr += String.valueOf(dayOfMonth);

                System.out.println(newDateStr);


                if(dateStr == "") {
                    dateViewer.setText("From" + newDateStr + " Until: ");
                    dateStr = newDateStr;
                    dateViewer.setVisibility(View.VISIBLE);
                }else {
                    countrySelector.setText(countrySelector.getText().toString() + " ");
                    countrySelector.setText(countrySelector.getText().toString().substring(0, countrySelector.getText().toString().length() - 1));
                    dateViewer.setVisibility(View.VISIBLE);
                    dateViewer.setText(dateViewer.getText().toString() + newDateStr);
                    calendar.setVisibility(View.GONE);
                    tripTypeRadioGroup.setVisibility(View.VISIBLE);
                    classTypeRadioGroup.setVisibility(View.VISIBLE);
                    itinenaryTypeText.setVisibility(View.VISIBLE);
                    classOfService.setVisibility(View.VISIBLE);
                    returnDateStr = newDateStr;
                    dateCorrect = true;
                }
            }
        });

        tripTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Check which radio button is selected
                RadioButton radioButton = group.findViewById(checkedId);
                if (radioButton != null) {
                    // Get the text of the selected radio button
                    String selectedText = radioButton.getText().toString();
                    System.out.println(selectedText);
                    flightItinerary = selectedText.replace(" ","_").toUpperCase();
                    countrySelector.setText(countrySelector.getText().toString() + " ");
                    countrySelector.setText(countrySelector.getText().toString().substring(0,countrySelector.getText().toString().length() - 1));

                    // Perform your desired actions with the selected radio button
                    Toast.makeText(getApplicationContext(), "Selected: " + selectedText, Toast.LENGTH_SHORT).show();
                }
            }
        });

        classTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Check which radio button is selected
                RadioButton radioButton = group.findViewById(checkedId);
                if (radioButton != null) {
                    // Get the text of the selected radio button
                    String selectedText = radioButton.getText().toString();
                    System.out.println(selectedText);
                    flightClass = selectedText.replace(" ","_").toUpperCase();
                    countrySelector.setText(countrySelector.getText().toString() + " ");
                    countrySelector.setText(countrySelector.getText().toString().substring(0,countrySelector.getText().toString().length() - 1));

                    // Perform your desired actions with the selected radio button
                    Toast.makeText(getApplicationContext(), "Selected: " + selectedText, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public void openFlightsChoosing(){
        Intent intent = new Intent(this, FlightChoosing.class);
        intent.putExtra("originAirport", originAirportName);
        intent.putExtra("destAirport", destAirportName);
        intent.putExtra("date", dateStr);
        intent.putExtra("itineraryType", flightItinerary);
        intent.putExtra("classOfService", flightClass);
        intent.putExtra("returnDate", returnDateStr);
        startActivity(intent);
    }


}