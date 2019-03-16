package com.piedpiper.epimaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HospitalRegister extends AppCompatActivity {

    TextView hospitalLocation;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_register);

        hospitalLocation=(TextView)findViewById(R.id.hospital_location);
        button=(Button)findViewById(R.id.location_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HospitalRegister.this,HospitalLocation.class);
                HospitalRegister.this.startActivity(intent);

            }
        });
        hospitalLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
