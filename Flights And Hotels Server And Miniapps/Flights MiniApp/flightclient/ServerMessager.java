package com.example.flightclient;


import android.util.Log;
import android.widget.Toast;

import com.example.flightclient.Server.ServerConnect;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServerMessager {

    private static ServerMessager instance;
    private String superapp = "2023.b.ahmad.massalha";

    private ServerMessager(){}

    public static ServerMessager getInstance() {
        if (instance == null) {
            instance = new ServerMessager();
        }
        return instance;
    }

    public Boolean validateEmail(String email){
        return true;
        //return ServerConnect.getInstance().login(superapp, email);

    }

    public ArrayList<String> getAirportsByCountry(String countryName){
        ArrayList<String> returnedValue = new ArrayList<String>();
        if(countryName.equals("new york")) {
            String stam[] = {"John F. Kennedy International Airport (JFK)", "LaGuardia Airport (LGA)", "Newark Liberty International Airport (EWR)", "Stewart International Airport (SWF)"
                    , "Westchester County Airport (HPN)", "Teterboro Airport (TEB)", "MacArthur Airport (ISP)", "Albany International Airport (ALB)"
                    , "Syracuse Hancock International Airport (SYR)", "Buffalo Niagara International Airport (BUF)"};
            returnedValue.addAll(Arrays.asList(stam));
            return returnedValue;
        }else {
            return returnedValue;
        }
    }

    public ArrayList<FlightData> getFlights(String origin, String dest , String date, String itineraryType, String classOfService , String returnDate){
        ArrayList<FlightData> flightList = new ArrayList<>();
        ServerConnect.getInstance().invokeGetFlightsByCheapestPrice("",origin,dest,date,itineraryType,classOfService , new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    ArrayList<FlightData> hotels = (ArrayList<FlightData>) response.body();
                    flightList.addAll(hotels);
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

        return flightList;
    }
}