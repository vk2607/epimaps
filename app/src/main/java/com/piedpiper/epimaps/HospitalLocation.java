package com.piedpiper.epimaps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.type.LatLng;

public class HospitalLocation extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private double latitude;
    private double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_location);
        MapFragment mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.hospitalLoc_Fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    mMap=googleMap;
        //LatLng mumbai=new LatLng(latitude,longitude);

    }
}
