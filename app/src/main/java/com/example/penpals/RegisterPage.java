package com.example.penpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.time.ZoneId;

public class RegisterPage extends AppCompatActivity {

    EditText etName, etAge, etCountry, etLanguages, etUsername, etPassword;
    Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        etName = (EditText) findViewById(R.id.name);
        etAge = (EditText) findViewById(R.id.age);
        etCountry = (EditText) findViewById(R.id.country);
        etLanguages = (EditText) findViewById(R.id.languages);
        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        bRegister = (Button) findViewById(R.id.register);

        bRegister.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(RegisterPage.this, LoginPage.class);
                        startActivity(i);
                    }
                }
        );
    }
}