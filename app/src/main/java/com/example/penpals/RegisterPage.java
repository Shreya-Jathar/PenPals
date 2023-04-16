package com.example.penpals;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> newUser = new HashMap<>();
        newUser.put("Name", name.getText().toString());
        newUser.put("Age", age.getText().toString());
        newUser.put("Country", country.getText().toString());
        newUser.put("Native Language", languages.getText().toString());

        if(PWD.isEmpty() || PWD.length() < 8) {
            password.setError("Password length must be at least 8 characters");
        }
        else {
            mAuth.createUserWithEmailAndPassword(USN, PWD).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    mUser = mAuth.getCurrentUser();
                    db.collection("Users").document(mUser.getUid()).set(newUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(RegisterPage.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterPage.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            Log.w("Error", e);
                        }
                    });
                    changeActivity();
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