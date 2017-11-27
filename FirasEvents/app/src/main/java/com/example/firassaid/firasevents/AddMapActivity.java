package com.example.firassaid.firasevents;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class AddMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Marker marker;
    String  lo,lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(34.7828307, 9.1251382);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));



        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng arg0) {
                if (marker != null) {
                    marker.remove();
                }
                lo = Double.toString(arg0.longitude);
                lat = Double.toString(arg0.latitude);
                marker = mMap.addMarker(new MarkerOptions()
                        .position(
                                new LatLng(arg0.latitude,
                                        arg0.longitude))
                        .draggable(true).visible(true));
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent in = new Intent(AddMapActivity.this,AddActivity.class);
        in.putExtra("long",lo);
        in.putExtra("lat",lat);
        startActivity(in);
    }
}
