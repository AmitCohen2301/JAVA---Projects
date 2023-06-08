// LoginActivity.java

package com.example.hotelsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelsapp.R;
import com.example.hotelsapp.Server.ServerConnect;
import com.example.hotelsapp.Server.boundaries.NewUserBoundary;
import com.example.hotelsapp.Server.boundaries.UserBoundary;
import com.example.hotelsapp.Server.boundaries.UserRole;
import com.example.hotelsapp.callbacks.CreateUserCallback;
import com.example.hotelsapp.callbacks.LoginCallBack;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements CreateUserCallback, LoginCallBack {
    public static final String KEY_BUNDLE = "KEY_BUNDLE";
    private Button login_BTN_signUp;
    private Button login_BTN_login;
    private TextInputEditText search_EDT_email;
    private TextInputEditText search_EDT_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        login_BTN_login = findViewById(R.id.login_BTN_signUp);
        login_BTN_signUp = findViewById(R.id.login_BTN_login);
        search_EDT_email = findViewById(R.id.search_EDT_email);
        search_EDT_username = findViewById(R.id.search_EDT_username);

        ServerConnect.getInstance().setCreateUserCallback(this);
        ServerConnect.getInstance().setLoginCallback(this
        );

        login_BTN_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = search_EDT_email.getText().toString();
                String username = search_EDT_username.getText().toString();
                NewUserBoundary newUserBoundary = new NewUserBoundary();
                newUserBoundary.setRole(UserRole.MINIAPP_USER);
                newUserBoundary.setUsername(username);
                if (!isValidUserName()) {
                    Toast.makeText(LoginActivity.this, "Invalid username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidEmail()) {
                    Toast.makeText(LoginActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                    return;
                }
                newUserBoundary.setEmail(email);
                newUserBoundary.setAvatar("NONE");

                ServerConnect.getInstance().createUser(newUserBoundary);
            }
        });

        login_BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = search_EDT_email.getText().toString();
                String username = search_EDT_username.getText().toString();
                ServerConnect.getInstance().login(DataManager.SUPER_APP, email);
            }
        });
    }

    @Override
    public void onCreateUserSuccess(UserBoundary userBoundary) {
        Toast.makeText(LoginActivity.this, "User created successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        String userBoundaryJson = new Gson().toJson(userBoundary);
        intent.putExtra("userBoundaryJson", userBoundaryJson);
        startActivity(intent);
        finish();

    }

    @Override
    public void onCreateUserFailure(Throwable throwable) {
        Toast.makeText(LoginActivity.this, "Failed to create user", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginSuccess(UserBoundary userBoundary) {
        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

        String enteredUsername = search_EDT_username.getText().toString();
        String loggedInUsername = userBoundary.getUsername();

        if (enteredUsername.equals(loggedInUsername)) {
            // The entered username matches the logged-in username
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            String userBoundaryJson = new Gson().toJson(userBoundary);
            intent.putExtra("userBoundaryJson", userBoundaryJson);
            startActivity(intent);

            finish();
        } else {
            // The entered username does not match the logged-in username
            Toast.makeText(LoginActivity.this, "Invalid username", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoginFailure(Throwable throwable) {
        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
    }
    private boolean isValidEmail() {
        String email = search_EDT_email.getText().toString();
        //Regular Expression
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean isValidUserName()
    {
        return !search_EDT_username.getText().toString().isEmpty();
    }
}
