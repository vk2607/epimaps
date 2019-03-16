package com.piedpiper.epimaps;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class AddPatient extends AppCompatActivity {
    RecyclerView recyclerView, recyclerView_1;
    LinearLayoutManager manager, manager_1;
    SymptomAdapter symptomAdapter;
    DiseaseAdapter diseaseAdapter;
    ArrayList<String> symptoms;
    ArrayList<String> diseases;
    boolean isScrolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        Button addpatientButton = (Button) findViewById(R.id.confirm_add_patient_button);
        addpatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddPatient.this, "Patient added successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddPatient.this, HospitalHomeScreen.class));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.symptoms_recyclerview);
        recyclerView_1 = (RecyclerView) findViewById(R.id.disease_recyclerview);
        manager = new LinearLayoutManager(this);
        manager_1 = new LinearLayoutManager(this);
        symptoms = new ArrayList<>();
        diseases = new ArrayList<>();

        symptoms.add("Cough");
        symptoms.add("Weakness");
        symptoms.add("Headache");
        symptoms.add("Breathing issues");

        diseases.add("Malaria");
        diseases.add("Dengue");
        diseases.add("Swine Flu");
        diseases.add("Common cold");
        diseases.add("Chicken pox");

        SymptomAdapter adapter = new SymptomAdapter(symptoms, this);
        DiseaseAdapter adapter1 = new DiseaseAdapter(diseases, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView_1.setAdapter(adapter1);
        recyclerView_1.setLayoutManager(manager_1);

    }
}
