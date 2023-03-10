package com.example.penpals;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    EditText name, age, country, languages, email, password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        country = findViewById(R.id.country);
        languages = findViewById(R.id.languages);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(
                view -> createAccount()
        );
    }

    private void createAccount() {
        String USN = email.getText().toString();
        String PWD = password.getText().toString();

        if(PWD.isEmpty() || PWD.length() < 8) {
            password.setError("Password length must be at least 8 characters");
        }
        else {
            mAuth.createUserWithEmailAndPassword(USN, PWD).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    mUser = mAuth.getCurrentUser();
                    changeActivity();
                    Toast.makeText(RegisterPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(RegisterPage.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void changeActivity() {
        Intent intent = new Intent(RegisterPage.this, HomePage.class);
        startActivity(intent);
    }
}