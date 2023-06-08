package com.example.flightclient.Server;

import android.util.Log;

import com.example.flightclient.Flight;
import com.example.flightclient.FlightData;
import com.example.flightclient.MainActivity;
import com.example.flightclient.Server.boundaries.InvokedBy;
import com.example.flightclient.Server.boundaries.MiniAppCommandBoundary;
import com.example.flightclient.Server.boundaries.ObjectId;
import com.example.flightclient.Server.boundaries.TargetObject;
import com.example.flightclient.Server.boundaries.UserBoundary;
import com.example.flightclient.Server.boundaries.UserId;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServerConnect {
    private static ServerConnect serverConnect = new ServerConnect();
    private final int STATUS_OK = 200;
    private ApiServer apiServer = RetrofitService.getInstance().getRetrofit().create(ApiServer.class);
    private MainActivity createUserCallback;
    private MainActivity loginCallback;
    private Boolean success = false;
    private String superapp = "2023.b.ahmad.massalha";
    private String email = "";

    public static ServerConnect getInstance() {
        return serverConnect;
    }



    public Boolean login(String superapp, String email) {
        this.email = email;
        apiServer.login(superapp, email).enqueue(new Callback<UserBoundary>() {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                Log.d("myLog", response.code() + "");
                if (response.isSuccessful()) {
                    success = true;
                } else {
                    success = false;
                }
            }

            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {
                //fail
            }
        });

        return success;
    }


    public void invokeGetFlightsByCheapestPrice(String command ,String origin,String dest ,String date, String itineraryType, String classOfService , Callback<Object> callback) {
        command = "searchflight";
        UserId userId = new UserId(superapp, email);
        InvokedBy invokedBy = new InvokedBy(userId);
        TargetObject targetObject = new TargetObject(new ObjectId("2023b.ahmad.massalha", "3973934e-c292-4134-8c8a-4c98724abf73"));
        Map<String, Object> commandAttributes = new HashMap<>();
        commandAttributes.put("sourceAirportCode", "JFK");
        commandAttributes.put("destinationAirportCode", "TEB");
        commandAttributes.put("date", date);
        commandAttributes.put("returnDate", date);
        commandAttributes.put("itineraryType", itineraryType);
        commandAttributes.put("classOfService", classOfService);
        commandAttributes.put("numAdults", 1);
        commandAttributes.put("numSeniors", 0);
        MiniAppCommandBoundary commandBoundary = new MiniAppCommandBoundary(command, targetObject, invokedBy, commandAttributes);
        apiServer.invokeCommand("ahmad", commandBoundary).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {

                    // Parse the JSON response into a list of HotelData objects
                    //List<HotelData> hotelDataList = parseHotelDataList(response.body());

                    // Create a list of Hotel objects from the HotelData list
                    //List<Hotel> hotels = new ArrayList<>();
                    List<FlightData> flightDataList = parseFlightDataList(response.body());


                    // Create a list of Hotel objects from the HotelData list
                    callback.onResponse(call, Response.success(flightDataList));
                } else {
                    callback.onFailure(call, new Throwable("Failed to get hotels"));
                }
            }


            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }


        });
    }

    public List<FlightData> parseFlightDataList(Object responseObj) {
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseObj);

        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
        JsonObject dataObject = jsonObject.getAsJsonObject("data");
        JsonArray flightsArray = dataObject.getAsJsonArray("flights");

        List<FlightData> flightDataList = new ArrayList<>();

        for (JsonElement flightElement : flightsArray) {
            JsonObject flightObject = flightElement.getAsJsonObject();
            JsonArray segmentsArray = flightObject.getAsJsonArray("segments");

            for (JsonElement segmentElement : segmentsArray) {
                JsonObject segmentObject = segmentElement.getAsJsonObject();
                JsonArray legsArray = segmentObject.getAsJsonArray("legs");

                for (JsonElement legElement : legsArray) {
                    JsonObject legObject = legElement.getAsJsonObject();

                    // Extract the properties of the leg and create a FlightData object
                    String originStationCode = legObject.get("originStationCode").getAsString();
                    String destinationStationCode = legObject.get("destinationStationCode").getAsString();
                    String departureDateTime = legObject.get("departureDateTime").getAsString();
                    String flightNumber = legObject.get("flightNumber").getAsString();
                    String classOfService = legObject.get("classOfService").getAsString();

                    FlightData flightData = new FlightData(originStationCode, destinationStationCode, departureDateTime, flightNumber, classOfService);
                    flightDataList.add(flightData);
                }
            }
        }

        return flightDataList;
    }


}
