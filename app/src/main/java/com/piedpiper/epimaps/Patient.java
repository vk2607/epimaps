package com.piedpiper.epimaps;

import com.google.firebase.Timestamp;

import java.util.ArrayList;

public class Patient {
    private String name, disease;
    private Boolean isValid;
    private Timestamp timestamp;
    private ArrayList<String> symptoms = new ArrayList<>();

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setSymptoms(ArrayList<String> symptoms) {
        this.symptoms = symptoms;
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setName(String patientName) {
        this.name = patientName;
    }

    public void setDisease(String diseaseName) {
        this.disease = diseaseName;
    }

    public String getName() {
        return name;
    }

    public String getDisease() {
        return disease;
    }
}
