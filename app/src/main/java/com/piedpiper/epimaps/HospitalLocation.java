package com.piedpiper.epimaps;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HospitalLocation extends AppCompatActivity implements OnMapReadyCallback , LocationListener {
  /* private GoogleMap mMap;
    private double latitude;
    private double longitude;
    private Location location;
    private Geocoder geocoder;
    private LocationManager locationManager;
    private List<Address> addresses;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   locationEditText = (EditText) findViewById(R.id.locationname_edittext);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location;
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
        MapFragment mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.hospitalLoc_Fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    mMap=googleMap;
    LatLng mumbai = new LatLng(latitude,longitude);
    mMap.addMarker(new MarkerOptions().position(mumbai).title("Set location"));
    mMap.moveCamera(CameraUpdateFactory.newLatLng(mumbai));
        try {
            locationEditText.setText(addresses.get(0).getSubLocality() + "," + addresses.get(0).getPostalCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

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
    }


    private void highlight() {
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }*/

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
//    final Geocoder geocoder=new Geocoder(this);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main) ;

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location;
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
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

    }

    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Set location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        try {
            locationEditText.setText(addresses.get(0).getSubLocality() + "," + addresses.get(0).getPostalCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                Log.d("First","first");
                mMap.clear();
                try {
                    Log.d("Second","second");
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Location"));
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    String locality = addresses.get(0).getLocality();
                    String postalcode = addresses.get(0).getPostalCode();
                    if (locality != null && postalcode != null) {
//                        locationEditText.setText(addresses.get(0).getLocality() + ", " + addresses.get(0).getPostalCode());
                    } else if (locality != null) {
//                        locationEditText.setText(addresses.get(0).getPostalCode());
                    } else {
//                        locationEditText.setText(null);
//                        locationEditText.setHint("Location not known ,please type pincode");
                    }

                } catch (IOException e) {
                    Toast.makeText(HospitalLocation.this, "IOExcept", Toast.LENGTH_SHORT).show();
                    locationEditText.setText(null);
                    locationEditText.setHint("Location not known ,please type pincode");
                    e.printStackTrace();
                } catch (IndexOutOfBoundsException e) {
                    Toast.makeText(HospitalLocation.this, "Indddex bdlm", Toast.LENGTH_SHORT).show();
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
}
