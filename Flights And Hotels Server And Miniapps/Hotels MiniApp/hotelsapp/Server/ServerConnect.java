package com.example.hotelsapp.Server;

import android.util.Log;

import com.example.hotelsapp.Server.boundaries.InvokedBy;
import com.example.hotelsapp.Server.boundaries.MiniAppCommandBoundary;
import com.example.hotelsapp.Server.boundaries.NewUserBoundary;
import com.example.hotelsapp.Server.boundaries.ObjectId;
import com.example.hotelsapp.Server.boundaries.TargetObject;
import com.example.hotelsapp.Server.boundaries.UserBoundary;
import com.example.hotelsapp.Server.boundaries.UserId;
import com.example.hotelsapp.activities.LoginActivity;
import com.example.hotelsapp.logic.Hotel;
import com.example.hotelsapp.logic.HotelData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
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
    private LoginActivity createUserCallback;
    private LoginActivity loginCallback;

    public static ServerConnect getInstance() {
        return serverConnect;
    }

    public void setCreateUserCallback(LoginActivity activity) {
        createUserCallback = activity;
    }

    public void setLoginCallback(LoginActivity activity) {
        loginCallback = activity;
    }

    public void createUser(NewUserBoundary newUserBoundary) {
        apiServer.createUser(newUserBoundary).enqueue(new Callback<UserBoundary>() {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                Log.d("myLog", response.code() + "");
                if (response.code() == STATUS_OK) {
                    if (createUserCallback != null) {
                        createUserCallback.onCreateUserSuccess(response.body());
                    }
                } else {
                    if (createUserCallback != null) {
                        createUserCallback.onCreateUserFailure(new Throwable("Failed to create user"));
                    }
                }
            }

            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {
                if (createUserCallback != null) {
                    createUserCallback.onCreateUserFailure(t);
                }
            }
        });
    }

    public void login(String superapp, String email) {
        apiServer.login(superapp, email).enqueue(new Callback<UserBoundary>() {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                Log.d("myLog", response.code() + "");
                if (response.isSuccessful()) {
                    if (loginCallback != null) {
                        loginCallback.onLoginSuccess(response.body());
                    }
                } else {
                    if (loginCallback != null) {
                        loginCallback.onLoginFailure(new Throwable("Login failed"));
                    }
                }
            }

            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {
                if (loginCallback != null) {
                    loginCallback.onLoginFailure(t);
                }
            }
        });
    }

    public void updateUser(String superapp, String email) {
        apiServer.updateUser(superapp, email).enqueue(new Callback<UserBoundary>() {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                // Handle update response
            }

            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public void invokeGetHotelsByCheapestPrice(String command,String superapp, String email, String query, int adults, int rooms, int night, String checkin, String checkout, Callback<Object> callback) {
        UserId userId = new UserId(superapp, email);
        InvokedBy invokedBy = new InvokedBy(userId);
        TargetObject targetObject = new TargetObject(new ObjectId("2023b.ahmad.massalha", "3973934e-c292-4134-8c8a-4c98724abf73"));
        Map<String, Object> commandAttributes = new HashMap<>();
        commandAttributes.put("query", query);
        commandAttributes.put("adults", adults);
        commandAttributes.put("rooms", rooms);
        commandAttributes.put("nights", night);
        commandAttributes.put("checkin", checkin);
        commandAttributes.put("checkout", checkout);
        MiniAppCommandBoundary commandBoundary = new MiniAppCommandBoundary(command, targetObject, invokedBy, commandAttributes);
        apiServer.invokeCommand("tal", commandBoundary).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    // Parse the JSON response into a list of HotelData objects
                    List<HotelData> hotelDataList = parseHotelDataList(response.body());

                    // Create a list of Hotel objects from the HotelData list
                    List<Hotel> hotels = new ArrayList<>();
                    for (int i = 1; i < hotelDataList.size(); i++) {
                        HotelData hotelData = hotelDataList.get(i);
                        Hotel hotel = new Hotel();
                        hotel.setTitle(hotelData.getTitle());
                        if (!hotelData.getCardPhotos().isEmpty()) {
                            hotel.setImage(hotelData.getCardPhotos().get(0).getSizes().getUrlTemplate());
                        } else {
                            // Handle the case when cardPhotos is empty
                            hotel.setImage(""); // Set a default image URL or handle it based on your requirements
                        }
                        hotel.setNumOfAdults(adults);  // You may need to update this depending on your JSON structure
                        if (hotelData.getPriceForDisplay() != null) {
                            if (hotelData.getPriceForDisplay().length() > 1) {
                                hotel.setPrice(Integer.parseInt(hotelData.getPriceForDisplay().substring(1)));
                            } else {
                                hotel.setPrice(0);
                            }
                        } else {
                            hotel.setPrice(0);
                        }
                        String review = hotelData.getBubbleRating().getCount();

                        int reviewNum = 0; // Default value if the rating is null
                        if (review != null) {
                            review = review.trim();
                            try {
                                reviewNum = Integer.parseInt(review);
                            } catch (NumberFormatException e) {
                                Log.e("myLog", "Failed to parse review: " + review);
                                e.printStackTrace();
                            }
                        }
                        hotel.setNumOfReviews(reviewNum);
                        String ratingString = hotelData.getBubbleRating().getRating();
                        double rating = 0.0; // Default value if the rating is null
                        if (ratingString != null) {
                            ratingString = ratingString.trim();
                            try {
                                rating = Double.parseDouble(ratingString);
                            } catch (NumberFormatException e) {
                                Log.e("myLog", "Failed to parse rating: " + ratingString);
                                e.printStackTrace();
                            }
                        }
                        hotel.setRating(rating);
                        hotels.add(hotel);
                    }

                    // Invoke the callback's onResponse method, passing the list of hotels
                    callback.onResponse(call, Response.success(hotels));
                } else {
                    callback.onFailure(call, new Throwable("Failed to get hotels"));
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public List<HotelData> parseHotelDataList(Object responseObj) {
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseObj);

        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
        JsonObject dataObject = jsonObject.getAsJsonObject("data");
        JsonArray dataArray = dataObject.getAsJsonArray("data");

        Type listType = new TypeToken<List<HotelData>>() {}.getType();
        return gson.fromJson(dataArray, listType);
    }






}
