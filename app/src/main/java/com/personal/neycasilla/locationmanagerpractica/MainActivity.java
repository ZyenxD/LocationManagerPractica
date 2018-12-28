package com.personal.neycasilla.locationmanagerpractica;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final int REQUEST_CODE = 100;
    private LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        loadLocation();
    }

    private void loadLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE);



        }
        Location location = locationManager
                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location!=null){
            Log.d("Main","loc: "+ location.getLongitude()+","+location.getLatitude());
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE){
            if(Manifest.permission.ACCESS_FINE_LOCATION.equals(permissions[0])
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && Manifest.permission.ACCESS_COARSE_LOCATION.equals(permissions[1])
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED){
             loadLocation();
            }

        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("Main","loc: "+ location.getLongitude()+","+location.getLatitude());
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
}
