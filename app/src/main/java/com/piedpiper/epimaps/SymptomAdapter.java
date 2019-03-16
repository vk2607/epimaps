package com.piedpiper.epimaps;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class SymptomAdapter extends RecyclerView.Adapter<SymptomAdapter.VHolder> {
    ArrayList<String> symptoms;
    Boolean checked[];
    Context context;
    int pos;

    public SymptomAdapter(ArrayList<String> symptoms, Context context) {
        this.symptoms = symptoms;
        this.context = context;
        this.checked = new Boolean[this.getItemCount()];
        for (int i = 0; i < getItemCount(); i++) {
            checked[i] = false;
        }
    }

    @NonNull
    @Override
    public SymptomAdapter.VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_symptoms, viewGroup, false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VHolder vHolder, final int position) {
        String symptom = symptoms.get(position);
        pos = position;
        vHolder.checkBox.setText(symptom);
        vHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checked[position] = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return symptoms.size();
    }

    public class VHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public VHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.symptomCheckBox);
        }
    }

    public Boolean[] getCheckedItems() {
        return checked;
    }

}
