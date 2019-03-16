package com.piedpiper.epimaps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddPatient extends AppCompatActivity {
    RecyclerView recyclerView, recyclerView_1;
    LinearLayoutManager manager, manager_1;
    SymptomAdapter symptomAdapter;
    DiseaseAdapter diseaseAdapter;
    ArrayList<String> symptoms;
    ArrayList<String> diseases;
    FirebaseFirestore fsClient;

    boolean isScrolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        final Button addpatientButton = (Button) findViewById(R.id.confirm_add_patient_button);
        addpatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddPatient.this, "Patient added successfully", Toast.LENGTH_SHORT).show();
                addPatient();
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
        diseases.add("Common Cold");
        diseases.add("Chicken Pox");

        symptomAdapter = new SymptomAdapter(symptoms, this);
        diseaseAdapter = new DiseaseAdapter(diseases, this);
        recyclerView.setAdapter(symptomAdapter);
        recyclerView.setLayoutManager(manager);
        recyclerView_1.setAdapter(diseaseAdapter);
        recyclerView_1.setLayoutManager(manager_1);

    }

    private void addPatient() {
        Boolean[] checkedSymptoms = symptomAdapter.getCheckedItems();
        Boolean[] checkedDiseases = diseaseAdapter.getCheckedItems();

        ArrayList<String> uploadSymptoms = new ArrayList<>();
        ArrayList<String> uploadDiseases = new ArrayList<>();

        for (int i = 0; i < symptoms.size(); i++) {
            if (checkedSymptoms[i]) {
                uploadSymptoms.add(symptoms.get(i));
            }
        }
        for (int i = 0; i < diseases.size(); i++) {
            if (checkedDiseases[i]) {
                uploadDiseases.add(diseases.get(i));
            }
        }

        EditText patientName = (EditText) findViewById(R.id.addPatient_edittext);
        String patientNameText = patientName.getText().toString();

        Timestamp timestamp = new Timestamp(new Date().getTime() / 1000, 0);

        Map<String, Object> update = new HashMap<>();
        update.put("name", patientNameText);
        update.put("symptoms", uploadSymptoms);
        update.put("diseases", uploadDiseases);
        update.put("isValid", true);
        update.put("timestamp", timestamp);

        fsClient = FirebaseFirestore.getInstance();
        String patientId = fsClient.collection("Hospitals")
                .document(FirebaseAuth.getInstance().getUid())
                .collection("Patients")
                .document()
                .getId();

        fsClient.collection("Hospitals")
                .document(FirebaseAuth.getInstance().getUid())
                .collection("Patients")
                .document(patientId)
                .set(update);

    }
}
