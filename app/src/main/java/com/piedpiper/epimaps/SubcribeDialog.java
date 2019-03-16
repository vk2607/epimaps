package com.piedpiper.epimaps;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SubcribeDialog extends DialogFragment {

    private View view;
    TextView email,city;
    Button cancel,ok,location;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.subscribe_dialog, null);

        Toast.makeText(getActivity(), "Oncreatedialog", Toast.LENGTH_SHORT).show();

        email=(TextView)view.findViewById(R.id.user_email);
        city=(TextView)view.findViewById(R.id.location_text);

        cancel=(Button)view.findViewById(R.id.cancel_btn);
        ok=(Button)view.findViewById(R.id.ok_btn);
        location=(Button)view.findViewById(R.id.location_btn);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Choose your Location", Toast.LENGTH_SHORT).show();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Thanks for the subscription", Toast.LENGTH_SHORT).show();
            }
        });

        return super.onCreateDialog(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Oncreateview", Toast.LENGTH_SHORT).show();
//        View v = inflater.inflate(R.layout.subscribe_dialog, container, false);
        email=(TextView)view.findViewById(R.id.user_email);
        city=(TextView)view.findViewById(R.id.location_text);

        cancel=(Button)view.findViewById(R.id.cancel_btn);
        ok=(Button)view.findViewById(R.id.ok_btn);
        location=(Button)view.findViewById(R.id.location_btn);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Choose your Location", Toast.LENGTH_SHORT).show();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Thanks for the subscription", Toast.LENGTH_SHORT).show();
            }
        });
        return inflater.inflate(R.layout.subscribe_dialog, null);
    }
}
