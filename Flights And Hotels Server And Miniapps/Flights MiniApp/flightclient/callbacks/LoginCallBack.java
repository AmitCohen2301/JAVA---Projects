package com.example.flightclient.callbacks;

import com.example.flightclient.Server.boundaries.UserBoundary;


public interface LoginCallBack  {
    void onLoginSuccess(UserBoundary userBoundary);
    void onLoginFailure(Throwable throwable);

}

