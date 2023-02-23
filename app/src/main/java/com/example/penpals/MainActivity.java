package com.example.penpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logIn = (Button) findViewById(R.id.logIn);
        Button signUp = (Button) findViewById(R.id.signUp);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        logIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this, LoginPage.class);
                        startActivity(i);
                    }
                }
        );

        signUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this, RegisterPage.class);
                        startActivity(i);
                    }
                }
        );


    }

    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        //if (currentUser != null)
            //reload();
    }
}