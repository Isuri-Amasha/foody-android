package com.example.foody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSignUpPage, btnLogInPage;
    TextView foody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignUpPage = (Button) findViewById(R.id.btnSignUpPage);
        btnLogInPage = (Button) findViewById(R.id.btnLogInPage);

        foody = (TextView) findViewById(R.id.foody);

        btnSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sign_Up = new Intent(MainActivity.this,SignUp.class);
                startActivity(Sign_Up);
            }
        });

        btnLogInPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Log_In = new Intent(MainActivity.this,LogIn.class);
                startActivity(Log_In);

            }
        });

    }
}