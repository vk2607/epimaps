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

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.VHolder> {
    ArrayList<String> diseases;
    Boolean[] checked;
    Context context;
    int pos;

    public DiseaseAdapter(ArrayList<String> diseases, Context context) {
        this.diseases = diseases;
        this.context = context;
        this.checked = new Boolean[this.getItemCount()];
        for (int i = 0; i < getItemCount(); i++) {
            checked[i] = false;
        }

    }

    @NonNull
    @Override
    public DiseaseAdapter.VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_disease, viewGroup, false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VHolder vHolder, final int position) {
        String disease = diseases.get(position);
        pos = position;
        vHolder.checkBox.setText(disease);
        vHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checked[position] = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return diseases.size();
    }

    public class VHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public VHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.diseasecheckBox);
        }
    }

    public Boolean[] getCheckedItems() {
        return checked;
    }

}
