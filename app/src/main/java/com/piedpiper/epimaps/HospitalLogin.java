package com.piedpiper.epimaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HospitalLogin extends AppCompatActivity {

    TextView registerHospital;
    Button loginButton;
//    String email,password,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_login);

        registerHospital=(TextView)findViewById(R.id.register_text);
        loginButton=(Button)findViewById(R.id.signin_button);

        registerHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HospitalLogin.this, "Register clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HospitalLogin.this,HospitalRegister.class));
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HospitalLogin.this,HospitalHomeScreen.class);
                startActivity(intent);
            }
        });
    }


}
