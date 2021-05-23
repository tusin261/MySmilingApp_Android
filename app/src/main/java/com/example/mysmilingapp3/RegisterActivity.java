package com.example.mysmilingapp3;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    EditText inputName,inputEmail,inputPassword1,InputPassword2;
    Button btnSignUp,btnSignIn,btnSignIn_google;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth auth =FirebaseAuth.getInstance();
        DatabaseReference myRef = database.getReference();


        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail_2);
        inputPassword1 = findViewById(R.id.inputPassword1_2);
        InputPassword2 = findViewById(R.id.inputPassword2_2);
        btnSignUp = findViewById(R.id.btnSignUp_2);
        btnSignIn = findViewById(R.id.btnSignIn_2);
        btnSignIn_google = findViewById(R.id.btnSignInGoogle_2);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,SignInActivity.class));
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = inputEmail.getText().toString().trim();
                String password = inputPassword1.getText().toString().trim();
                String password2 = InputPassword2.getText().toString().trim();
                String name = inputName.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Nhap password zo!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(), "Nhap ten nguoi dung !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password2.equals(password)){
                    Toast.makeText(getApplicationContext(), "Mat khau 2 khong chinh xac !", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Person p = new Person(name,email,0,0,0);
                                Random random = new Random();
                                int id = random.nextInt(1000);
                                //DatabaseReference myRef2 = myRef.child("users");

                                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = current_user.getUid();
                                HashMap<String, String> map = new HashMap<>();
                                //map.put("id", id + "");
                                map.put("name", p.getName());
                                map.put("happy", p.getHappy() + "");
                                map.put("normal", p.getNornal() + "");
                                map.put("unhappy", p.getUnhappy() + "");
                                myRef.child("Users").child(uid).setValue(map);
                                //myRef2.setValue("ok");

                                //HashMap<String,String> map = new HashMap<>();
                               // map.put(email,"ok");

                                //DatabaseReference myRef3 = myRef2.child(email);
                                //myRef3.setValue(p);
                                //Map<String, Person> users = new HashMap<>();
                                //users.put(email, new Person(name, email,0,0,0));
                                //users.put("gracehop", new Person("December 9, 1906", "Grace Hopper"));
                                //myRef2.setValue(users);
                                //myRef2.setValue(map);
                                if(task.isSuccessful()){
                                    startActivity(new Intent(RegisterActivity.this,SignInActivity.class));
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Dang ki that bai", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });



            }


        });





    }
}