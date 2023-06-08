package com.example.flightclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.regex.*;

public class MainActivity extends AppCompatActivity {
    //TextInputLayout textInputLayout = findViewById(R.id.inputText);
    TextView inputViewer;
    EditText inputText;
    Button confirmButton;
    TextView emailViewer;
    ServerMessager serverMessager = ServerMessager.getInstance(); // server messager singlton


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Hello World");

        inputText = (EditText) findViewById(R.id.inputText);
        inputViewer = (TextView) findViewById(R.id.inputViewer);
        confirmButton = (Button) findViewById(R.id.myButton);
        emailViewer = (TextView) findViewById(R.id.emailViewer);
        confirmButton.setVisibility(View.GONE);


        //Text watcher

        inputText.addTextChangedListener(new TextWatcher() {
            Boolean textChanged = false;
            @Override
            // when there is no text added
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().trim().length() == 0) {
                    // set text to Not typing
                    if(!textChanged)
                        confirmButton.setVisibility(View.GONE);
                } else {
                    // set text to typing
                    confirmButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Email validation
                //Lights up confirm button if email regex match
                String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
                Pattern pattern = Pattern.compile(emailRegex);
                if (pattern.matcher(inputText.getText().toString()).matches()) {
                    confirmButton.setClickable(true);
                    confirmButton.setBackgroundColor(Color.BLUE);
                }else{
                    confirmButton.setClickable(false);
                    confirmButton.setBackgroundColor(Color.parseColor("#dedfe0"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void confirmPressed(View view) {
        System.out.println("Email:" + inputText.getText().toString() + " Validating");
        emailViewer.setText("Validating email...");
        if(serverMessager.validateEmail(inputText.getText().toString())){
            System.out.println("Going to next activity");
            emailViewer.setText("Great!");
            openAirportChoosing();
        }else {
            emailViewer.setText("Email not in system, please try again");
        }
        //inputText.setVisibility(View.GONE);
    }

    public void openAirportChoosing(){
        Intent intent = new Intent(this, ChoosingAirports.class);
        startActivity(intent);
    }
}