package com.example.penpals;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AccountPage extends AppCompatActivity {

    Button logOut;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);

        logOut = findViewById(R.id.logOut);
        mAuth = FirebaseAuth.getInstance();

        logOut.setOnClickListener(view -> {
            mAuth.signOut();
            Intent intent = new Intent(AccountPage.this, MainActivity.class);
            startActivity(intent);
        });
    }
}