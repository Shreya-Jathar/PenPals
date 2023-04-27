package com.example.penpals;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    EditText email, password;
    Button logIn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        logIn = findViewById(R.id.logIn);

        mAuth = FirebaseAuth.getInstance();

        logIn.setOnClickListener(view -> signIn());
    }

    private void signIn() {
        String USN = email.getText().toString();
        String PWD = password.getText().toString();

        mAuth.signInWithEmailAndPassword(USN, PWD).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                changeActivity();
                Toast.makeText(LoginPage.this, "LogIn Successful", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(LoginPage.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
        });
    }

    private void changeActivity() {
        Intent intent = new Intent(LoginPage.this, HomePage.class);
        startActivity(intent);
    }
}