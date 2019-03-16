package com.piedpiper.epimaps;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.content.Context;
import android.location.LocationManager;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.graphics.Region.Op;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, LocationListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private GoogleMap mMap;
    private LocationManager mLocationManager;
    private double latitude;
    private double longitude;
    private EditText locationEditText;
    private Geocoder geocoder;
    private List<Address> addresses;
    private Location location;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseFirestore fsClient;
//    final Geocoder geocoder=new Geocoder(this);


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        locationEditText = (EditText) findViewById(R.id.locationname_edittext);
//        toolbar.setTitle("Map");
//        setSupportActionBar(toolbar);

        Toast.makeText(this,"In main",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,Graph.class));

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        geocoder = new Geocoder(this, Locale.getDefault());

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


//        fragmentManager = getFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.commit();

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
        defaultLogin();

    }

    private void defaultLogin() {
        currentUser=mAuth.getCurrentUser();
        String userId;
        if (currentUser != null && currentUser.isEmailVerified()) {
            userId = currentUser.getUid();
            startActivity(new Intent(MainActivity.this, HospitalHomeScreen.class));
            finish();
        }
    }

//    @Override
//    public void onLocationChanged(Location location) {
//        this.location = location;
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.main_nav_login) {
            startActivity(new Intent(this, HospitalLogin.class));
        } else if (id == R.id.main_nav_subscribe) {
            SubcribeDialog dialog = new SubcribeDialog();
            dialog.show(getFragmentManager(), "subscriber dialog");


        } else if (id == R.id.main_nav_feedback) {

        } else if (id == R.id.main_nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        locationEditText.setText(addresses.get(0).getSubLocality() + ", " + addresses.get(0).getPostalCode());

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                try {
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Location"));
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    String locality = addresses.get(0).getLocality();
                    String postalcode = addresses.get(0).getPostalCode();
                    if (locality != null && postalcode != null) {
                        locationEditText.setText(addresses.get(0).getLocality() + ", " + addresses.get(0).getPostalCode());
                    } else if (locality != null) {
                        locationEditText.setText(addresses.get(0).getPostalCode());
                    } else {
                        locationEditText.setText(null);
                        locationEditText.setHint("Location not known ,please type pincode");
                    }

                } catch (IOException e) {
                    locationEditText.setText(null);
                    locationEditText.setHint("Location not known ,please type pincode");
                    e.printStackTrace();
                } catch (IndexOutOfBoundsException e) {
                    locationEditText.setText(null);
                    e.printStackTrace();
                    locationEditText.setHint("Location not known ,please type pincode");

                }
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            }
        });
        highlight();


        // Try to obtain the map from the SupportMapFragment.

    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public void highlight() {
//        CircleOptions circleOptions = new CircleOptions()
//                .center(new LatLng(latitude, longitude))
//                .radius(100)
//                .fillColor(Color.RED);
//
//        Circle circle = mMap.addCircle(circleOptions);
//        mMap.an
    }

    public void getPosition() {

    }

    //    private void drawMarker(Location location) {
//        if (mMap != null) {
//            mMap.clear();
//            LatLng gps = new LatLng(location.getLatitude(), location.getLongitude());
//            mMap.addMarker(new MarkerOptions()
//                    .position(gps)
//                    .title("Current Position"));
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gps, 12));
//        }
//
//    }
//    public int getZoomLevel(Circle circle) {
//        int zoomLevel = 11;
//        if (circle != null) {
//            double radius = circle.getRadius() + circle.getRadius() / 2;
//            double scale = radius / 500;
//            zoomLevel = (int) (16 - Math.log(scale) / Math.log(2));
//        }
//        return zoomLevel;
//    }
//

}
