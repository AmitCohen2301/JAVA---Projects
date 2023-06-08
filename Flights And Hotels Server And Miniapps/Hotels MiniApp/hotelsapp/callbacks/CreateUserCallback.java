package com.example.hotelsapp.callbacks;

import com.example.hotelsapp.Server.boundaries.UserBoundary;

public interface CreateUserCallback {
    void onCreateUserSuccess(UserBoundary userBoundary);

    void onCreateUserFailure(Throwable throwable);
}