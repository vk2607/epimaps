package com.piedpiper.epimaps;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.VHolder> {
    ArrayList<Patient>patients;
    Context context;
    int pos;
    public PatientAdapter(ArrayList<Patient>patients,Context context)
    {
        this.patients=patients;
        this.context=context;
    }
    @NonNull
    @Override
    public PatientAdapter.VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_patient, viewGroup, false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VHolder vHolder, int position) {
    Patient patient=patients.get(pos);
    pos=position;
    vHolder.name.setText(patient.getName());
    vHolder.disease.setText(patient.getDisease());

    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public class VHolder extends RecyclerView.ViewHolder {
        TextView name,disease;
        public VHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.patientName_text);
            disease=(TextView)itemView.findViewById(R.id.diseaseName_text);
        }
    }
}
