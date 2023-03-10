package com.example.penpals;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logIn = (Button) findViewById(R.id.logIn);
        Button signUp = (Button) findViewById(R.id.signUp);

        mAuth = FirebaseAuth.getInstance();

        logIn.setOnClickListener(
                view -> {
                    Intent i = new Intent(MainActivity.this, LoginPage.class);
                    startActivity(i);
                }
        );

        signUp.setOnClickListener(
                view -> {
                    Intent i = new Intent(MainActivity.this, RegisterPage.class);
                    startActivity(i);
                }
        );


    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
            changeActivity();

    }

    private void changeActivity() {
        Intent intent = new Intent(MainActivity.this, HomePage.class);
        startActivity(intent);
    }
}