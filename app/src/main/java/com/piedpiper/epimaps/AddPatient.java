package com.piedpiper.epimaps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class AddPatient extends AppCompatActivity {
    RecyclerView recyclerView,recyclerView_1;
    LinearLayoutManager manager,manager_1;
    SymptomAdapter symptomAdapter;
    DiseaseAdapter diseaseAdapter;
    ArrayList<String> symptoms;
    ArrayList<String> diseases;
    boolean isScrolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        recyclerView = (RecyclerView) findViewById(R.id.symptoms_recyclerview);
        recyclerView_1=(RecyclerView)findViewById(R.id.disease_recyclerview);
        manager = new LinearLayoutManager(this);
        manager_1=new LinearLayoutManager(this);
        symptoms = new ArrayList<>();
        diseases=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            symptoms.add("Cough");
        }
        for (int i = 0; i < 3; i++) {
            diseases.add("Dengue");
        }
        SymptomAdapter adapter = new SymptomAdapter(symptoms, this);
        DiseaseAdapter adapter1=new DiseaseAdapter(diseases,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView_1.setAdapter(adapter1);
        recyclerView_1.setLayoutManager(manager_1);

    }
}
