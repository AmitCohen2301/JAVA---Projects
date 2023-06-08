package com.example.hotelsapp.callbacks;

import com.example.hotelsapp.Server.boundaries.UserBoundary;


public interface LoginCallBack  {
    void onLoginSuccess(UserBoundary userBoundary);
    void onLoginFailure(Throwable throwable);

}

