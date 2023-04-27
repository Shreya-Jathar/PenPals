package com.example.penpals;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class HomePage extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    LinearLayoutManager linearLayoutManager;
    private FirebaseAuth firebaseAuth;
    FirestoreRecyclerAdapter<firebaseModel, NoteViewHolder> chatAdapter;
    RecyclerView recyclerView;
    FirebaseUser currentUser;
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button account = findViewById(R.id.account);
        Button newPal = findViewById(R.id.newPal);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recycler);
        currentUser = firebaseAuth.getCurrentUser();
        UID = currentUser.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection("Users").document(UID);

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

        Query query = firebaseFirestore.collection("Users").whereNotEqualTo("NativeLanguage", ref.get().getResult().getString("NativeLanguage"));
        FirestoreRecyclerOptions<firebaseModel> matchedUSers =
                new FirestoreRecyclerOptions.Builder<firebaseModel>().setQuery(query, firebaseModel.class).build();
        chatAdapter = new FirestoreRecyclerAdapter<firebaseModel, NoteViewHolder>(matchedUSers) {
            @Override
            protected void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int position, @NonNull firebaseModel model) {
                noteViewHolder.userName.setText(firebaseModel.getName());
                noteViewHolder.userLanguage.setText(firebaseModel.getNativeLanguage());

                noteViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
            }

            @NonNull
            @Override
            public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.chatview, parent, false);
                return new NoteViewHolder(view);
            }
        };

        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(chatAdapter);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView userName, userLanguage;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.UserName);
            userLanguage = itemView.findViewById(R.id.UserLanguage);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        chatAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(chatAdapter != null) {
            chatAdapter.stopListening();
        }
    }
}