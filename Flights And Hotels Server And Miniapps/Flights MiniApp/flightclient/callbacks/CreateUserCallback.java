package com.example.flightclient.callbacks;

import com.example.flightclient.Server.boundaries.UserBoundary;

public interface CreateUserCallback {
    void onCreateUserSuccess(UserBoundary userBoundary);

    void onCreateUserFailure(Throwable throwable);
}