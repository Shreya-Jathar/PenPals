package com.example.penpals;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AccountPage extends AppCompatActivity {

    Button logOut;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextView name, age, country, nativeLang;
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);

        logOut = findViewById(R.id.logOut);
        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.nameVal);
        age = findViewById(R.id.ageVal);
        country = findViewById(R.id.countryVal);
        nativeLang = findViewById(R.id.nativeLangVal);
        logOut.setOnClickListener(view -> {
            mAuth.signOut();
            Intent intent = new Intent(AccountPage.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void onStart() {
        super.onStart();

        mUser = mAuth.getCurrentUser();
        UID = mUser.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection("Users").document(UID);

        ref.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult().exists()) {
                            name.setText(task.getResult().getString("Name"));
                            age.setText(task.getResult().getString("Age"));
                            country.setText(task.getResult().getString("Country"));
                            nativeLang.setText(task.getResult().getString("NativeLanguage"));
                        }
                        else {
                            return;
                        }
                    }
                });
    }
}