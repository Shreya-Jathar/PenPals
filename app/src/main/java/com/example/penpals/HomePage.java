package com.example.penpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button account = findViewById(R.id.account);
        Button newPal = findViewById(R.id.newPal);

        account.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(HomePage.this, AccountPage.class);
                        startActivity(i);
                    }
                }
        );

        newPal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(HomePage.this, HomePage.class);
                        startActivity(i);
                    }
                }
        );
    }
}