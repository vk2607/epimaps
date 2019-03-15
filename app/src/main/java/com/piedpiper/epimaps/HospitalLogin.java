package com.piedpiper.epimaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HospitalLogin extends AppCompatActivity {

    TextView registerHospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_login);

        registerHospital=(TextView)findViewById(R.id.register_text);

        registerHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HospitalLogin.this,HospitalRegister.class));
            }
        });
    }
}
