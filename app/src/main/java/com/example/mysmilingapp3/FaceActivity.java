package com.example.mysmilingapp3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FaceActivity extends AppCompatActivity {

    ImageButton btnHappy,btnNormal,btnUnhappy;
    Button btnFinish;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth auth =FirebaseAuth.getInstance();

    FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);


        btnHappy = findViewById(R.id.btnHappy);
        btnNormal = findViewById(R.id.btnNormal);
        btnUnhappy = findViewById(R.id.btnUnhappy);
        btnFinish = findViewById(R.id.btnFinish);

        btnHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = database.getReference("Users").child(user.getUid()).child("happy");
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int h = Integer.parseInt(snapshot.getValue().toString());
                            h++;
                            myRef.setValue(h);
                            Toast.makeText(FaceActivity.this,h+"",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

        });

        btnUnhappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = database.getReference("Users").child(user.getUid()).child("unhappy");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int u = Integer.parseInt(snapshot.getValue().toString());
                        u++;
                        myRef.setValue(u);
                        Toast.makeText(FaceActivity.this,u+"",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = database.getReference("Users").child(user.getUid()).child("normal");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int n = Integer.parseInt(snapshot.getValue().toString());
                        n++;
                        myRef.setValue(n);
                        Toast.makeText(FaceActivity.this,n+"",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}