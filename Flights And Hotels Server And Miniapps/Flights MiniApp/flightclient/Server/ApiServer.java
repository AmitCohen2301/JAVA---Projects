package com.example.flightclient.Server;


import com.example.flightclient.Server.boundaries.MiniAppCommandBoundary;
import com.example.flightclient.Server.boundaries.NewUserBoundary;
import com.example.flightclient.Server.boundaries.UserBoundary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiServer {
    @POST("/superapp/users")
     Call<UserBoundary> createUser(@Body NewUserBoundary newUserBoundary) ;
    @GET("/superapp/users/login/{superapp}/{email}")
    Call<UserBoundary> login(@Path("superapp") String superapp , @Path("email") String email) ;
    @PUT("/superapp/users/login/{superapp}/{email}")
    Call<UserBoundary> updateUser(@Path("superapp") String superapp , @Path("email") String email) ;
    @POST("/superapp/miniapp/{miniAppName}")
    Call<Object> invokeCommand(@Path("miniAppName" )String miniAppName,@Body MiniAppCommandBoundary miniAppCommandBoundaryiniAppCommandBoundary) ;

}
