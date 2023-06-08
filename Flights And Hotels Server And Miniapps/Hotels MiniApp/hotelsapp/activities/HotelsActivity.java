package com.example.hotelsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelsapp.Server.ServerConnect;
import com.example.hotelsapp.Server.boundaries.UserBoundary;
import com.example.hotelsapp.logic.Hotel;
import com.example.hotelsapp.R;
import com.example.hotelsapp.activities.fragments.Adapter_Hotel;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelsActivity extends AppCompatActivity {

    public static final String KEY_BUNDLE = "KEY_BUNDLE";
    public static final String KEY_DEST = "KEY_DEST";
    public static final String KEY_CHECK_IN = "KEY_CHECK_IN";
    public static final String KEY_CHECK_OUT = "KEY_CHECK_OUT";
    public static final String KEY_ADULTS = "KEY_ADULTS";
    public static final String KEY_ROOMS = "KEY_ROOMS";

    private Bundle bundle;

    private RecyclerView main_LST_hotels;
    private AppCompatImageView main_IMG_background;
    private ArrayList<Hotel> hotels;
    private MaterialButton price ;
    private MaterialButton center ;
    private MaterialButton pop ;
    private AppCompatImageButton back;
    String search_EDT_dest;
    int adults ;
    int rooms ;
    int night ;
    String checkIn ;
    String checkOut ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        bundle = getIntent().getBundleExtra(MainActivity.KEY_BUNDLE);
        String userBoundaryJson = getIntent().getStringExtra("userBoundaryJson");

        Bundle extras = getIntent().getExtras();
        search_EDT_dest = "";
        adults = 0;
        rooms = 0;
        night = 0;
        checkIn = "";
        checkOut = "";

        if (extras != null) {
            search_EDT_dest = extras.getString(KEY_DEST);
            checkIn = extras.getString(KEY_CHECK_IN);
            checkOut = extras.getString(KEY_CHECK_OUT);
            adults = extras.getInt(KEY_ADULTS);
            rooms = extras.getInt(KEY_ROOMS);
        }

        UserBoundary userBoundary = new Gson().fromJson(userBoundaryJson, UserBoundary.class);

        findViews();
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchHotels("gethotelsbycheapestprice" ,userBoundary,search_EDT_dest, adults, rooms, night, checkIn, checkOut);
            }
        });
        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchHotels("gethotelbytravellerranking" ,userBoundary,search_EDT_dest, adults, rooms, night, checkIn, checkOut);
            }
        });
        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchHotels("gethotelbytravellerranking" ,userBoundary,search_EDT_dest, adults, rooms, night, checkIn, checkOut);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelsActivity.this, MainActivity.class);
                String userBoundaryJson = new Gson().toJson(userBoundary);
                intent.putExtra("userBoundaryJson", userBoundaryJson);
                startActivity(intent);
                finish();
            }
        });

    }

    private void findViews() {
        main_LST_hotels = findViewById(R.id.main_LST_hotels);
        main_IMG_background = findViewById(R.id.main_IMG_background);

        // Set the background image
        main_IMG_background.setImageResource(R.drawable.img_pattern_big);
        price=findViewById(R.id.price);
        center=findViewById(R.id.center);
        pop=findViewById(R.id.pop);
        back=findViewById(R.id.back);
    }

    private void fetchHotels(String command,UserBoundary userBoundary,String destination, int adults, int rooms, int night, String checkIn, String checkOut) {
        ServerConnect.getInstance().invokeGetHotelsByCheapestPrice(command,userBoundary.getUserId().getSuperapp(), userBoundary.getUserId().getEmail(), destination, adults, rooms, night, checkIn, checkOut, new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    ArrayList<Hotel> hotels = (ArrayList<Hotel>) response.body();
                    initList(hotels);
                } else {
                    Toast.makeText(HotelsActivity.this, "Failed to fetch hotels", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(HotelsActivity.this, "Failed to fetch hotels", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void initList(ArrayList<Hotel> hotels) {
        Adapter_Hotel adapter_hotel = new Adapter_Hotel(hotels);
        adapter_hotel.setOnHotelClickListener(new Adapter_Hotel.OnHotelClickListener() {
            @Override
            public void onClick(View view, Hotel hotel, int position) {
                Toast.makeText(HotelsActivity.this, hotel.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
            }
        });

        main_LST_hotels.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        main_LST_hotels.setHasFixedSize(true);
        main_LST_hotels.setAdapter(adapter_hotel);
    }
}
