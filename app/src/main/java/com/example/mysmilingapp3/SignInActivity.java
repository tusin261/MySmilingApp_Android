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

public class SignInActivity extends AppCompatActivity {
    EditText inputEmail_1,inputPassword_2;
    Button btnSignIn_1,btnResetPassword,btnSignInGoogle,btnSignUp;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        auth = FirebaseAuth.getInstance();



        inputEmail_1 = findViewById(R.id.inputEmail_1);
        inputPassword_2 = findViewById(R.id.inputPassword_2);
        btnSignIn_1 = findViewById(R.id.btnSignIn_1);
        btnResetPassword = findViewById(R.id.btn_reset_password);
        btnSignInGoogle = findViewById(R.id.btnSignInGoogle_1);
        btnSignUp = findViewById(R.id.btnSignUp_1);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, RegisterActivity.class));
            }
        });

        btnSignIn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail_1.getText().toString().trim();
                String password = inputPassword_2.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                        SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword_2.setError("Sai mk");
                                    } else {
                                        Toast.makeText(SignInActivity.this, "Sai mk", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(SignInActivity.this, FaceActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });


            }
        });


    }
}