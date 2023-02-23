package com.example.penpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button bLogIn;
    TextView tvRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        bLogIn = (Button) findViewById(R.id.logIn);
        tvRegisterLink = (TextView) findViewById(R.id.registerLink);

        bLogIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(LoginPage.this, HomePage.class);
                        startActivity(i);
                    }
                }
        );

        tvRegisterLink.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(LoginPage.this, RegisterPage.class);
                        startActivity(i);
                    }
                }
        );
    }
}