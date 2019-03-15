package com.piedpiper.epimaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HospitalRegister extends AppCompatActivity {

    TextView hospitalLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_register);

        hospitalLocation=(TextView)findViewById(R.id.hospital_location);

        hospitalLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HospitalRegister.this,Location.class));
            }
        });
    }
}
