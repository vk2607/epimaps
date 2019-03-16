package com.piedpiper.epimaps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HospitalRegister extends AppCompatActivity {
    private FirebaseAuth regAuth;
    TextView hospitalLocation;
    Button button;
    EditText emailEditText, nameEditText, passwordEditText;
    Button requestVerificationButton;
    String name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_register);
        regAuth = FirebaseAuth.getInstance();

        button = (Button) findViewById(R.id.setlocation_regiser_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalRegister.this, HospitalLocation.class);
                startActivity(intent);
            }
        });
        hospitalLocation = (TextView) findViewById(R.id.setlocation_regiser_button);
        emailEditText = (EditText) findViewById(R.id.email_edittext);
        nameEditText = (EditText) findViewById(R.id.name_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        requestVerificationButton = (Button) findViewById(R.id.requestverification_button);


//        hospitalLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        emailAuthentcation();

    }

    private void emailAuthentcation() {
        requestVerificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                name = nameEditText.getText().toString();
                if (!email.isEmpty() && !name.isEmpty() && !password.isEmpty()) {
                    regAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(HospitalRegister.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                regAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(HospitalRegister.this, "Click on the verification link and sign in", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(HospitalRegister.this, HospitalLogin.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(HospitalRegister.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(HospitalRegister.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                } else {
                    Toast.makeText(HospitalRegister.this, "Fill all the information", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
