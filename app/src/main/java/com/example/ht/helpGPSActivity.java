package com.example.ht;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
//import android.location.LocationListener;

import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

//地圖設定參考:https://www.youtube.com/watch?v=PVlN_dZhAfw
//連接GPS參考:https://www.youtube.com/watch?v=4kk-dYWVNsc

public class helpGPSActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currrentUserLocationMarker;
    private static final int Request_User__Location_Code = 99;
    TextView hottea;
    ImageButton menubutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_gps);

        hottea = (TextView)findViewById(R.id.textHotTea);
        menubutton = (ImageButton)findViewById(R.id.menubutton);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkUserLocationPermission();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


            buildGoogleApiClient();

            mMap.setMyLocationEnabled(true);
        }


        // Add a marker in Sydney, Australia, and move the camera.
        LatLng taipei1 = new LatLng(25.052365, 121.517142);
        mMap.addMarker(new MarkerOptions().position(taipei1).title("財團法人加惠心理諮商文教基金會"));

        LatLng taipei2 = new LatLng(25.046251, 121.519418);
        mMap.addMarker(new MarkerOptions().position(taipei2).title("懷仁全人發展中心"));

        LatLng taipei3 = new LatLng(25.053774, 121.529931);
        mMap.addMarker(new MarkerOptions().position(taipei3).title("臺北市學生輔導諮商中心"));

        LatLng k1 = new LatLng(22.640228, 120.333097);
        mMap.addMarker(new MarkerOptions().position(k1).title("高雄市學生輔導諮商中心"));

        LatLng k2 = new LatLng(22.630446, 120.321350);
        mMap.addMarker(new MarkerOptions().position(k2).title("財團法人勵馨社會福利基金會"));

        LatLng k3 = new LatLng(22.618772, 120.302867);
        mMap.addMarker(new MarkerOptions().position(k3).title("財團法人張老師基金會"));

        LatLng p1 = new LatLng(22.680533, 120.486037);
        mMap.addMarker(new MarkerOptions().position(p1).title("屏東縣學生輔導諮商中心"));

        LatLng ncu1 = new LatLng(24.993755, 121.314163);
        mMap.addMarker(new MarkerOptions().position(ncu1).title("財團法人張老師基金會"));

        LatLng ncu2 = new LatLng(24.951137, 121.215510);
        mMap.addMarker(new MarkerOptions().position(ncu2).title("財團法人張老師基金會"));

        LatLng ncu3 = new LatLng(25.011427, 121.298729);
        mMap.addMarker(new MarkerOptions().position(ncu3).title("桃園市生命線協會"));

        LatLng hsin1 = new LatLng(24.733994, 121.093370);
        mMap.addMarker(new MarkerOptions().position(hsin1).title("新竹縣學生輔導諮商中心"));

        LatLng hsin2 = new LatLng(24.801485, 120.963054);
        mMap.addMarker(new MarkerOptions().position(hsin2).title("救國團新竹張老師中心"));

        LatLng home1 = new LatLng(24.163074, 120.678505);
        mMap.addMarker(new MarkerOptions().position(home1).title("財團法人張老師基金會台中分事務所"));

        LatLng miao1 = new LatLng(24.566215, 120.824320);
        mMap.addMarker(new MarkerOptions().position(miao1).title("苗栗縣寬心自在協會"));

        LatLng c1 = new LatLng(24.078004, 120.545626);
        mMap.addMarker(new MarkerOptions().position(c1).title("財團法人張老師基金會"));

        LatLng yun1 = new LatLng(23.697855, 120.527329);
        mMap.addMarker(new MarkerOptions().position(yun1).title("雲林縣社區心理衛生中心"));

        LatLng chia1 = new LatLng(23.489136, 120.452658);
        mMap.addMarker(new MarkerOptions().position(chia1).title("財團法人張老師基金會"));

        LatLng nan1 = new LatLng(22.980510, 120.223267);
        mMap.addMarker(new MarkerOptions().position(nan1).title("財團法人張老師基金會臺南中心"));

        LatLng p2 = new LatLng(22.680258, 120.486090);
        mMap.addMarker(new MarkerOptions().position(p2).title("屏東縣學生輔導諮商中心"));

        LatLng y1 = new LatLng(24.758920, 121.748243);
        mMap.addMarker(new MarkerOptions().position(y1).title("宜蘭縣社區心理衛生中心"));

        LatLng hua1 = new LatLng(23.971143, 121.585437);
        mMap.addMarker(new MarkerOptions().position(hua1).title("花蓮縣學生諮商輔導中心"));

        LatLng tung1 = new LatLng(22.751923, 121.144898);
        mMap.addMarker(new MarkerOptions().position(tung1).title("台東縣學生輔導諮商中心"));

        LatLng g1 = new LatLng(25.121057, 121.723325);
        mMap.addMarker(new MarkerOptions().position(g1).title("基隆市衛生局心理衛生中心"));

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(middle));
    }

    public boolean checkUserLocationPermission()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User__Location_Code);
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User__Location_Code);
            }
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case Request_User__Location_Code:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                   if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION )== PackageManager.PERMISSION_GRANTED)
                   {
                       if(googleApiClient == null)
                       {
                           buildGoogleApiClient();
                       }
                       mMap.setMyLocationEnabled(true);
                   }
                }
                else
                {
                    Toast.makeText(this, "Permission Denied QQ", Toast.LENGTH_SHORT);
                }
                return;
        }
    }

    protected synchronized void buildGoogleApiClient(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();

        //LatLng middle = new LatLng(24.146979,120.7338515);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(middle));
    }

    @Override
    public void onLocationChanged(Location location) {

        lastLocation = location;

        if(currrentUserLocationMarker != null){
            currrentUserLocationMarker.remove();
        }

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("你在這裡");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

        currrentUserLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(4));

        if(googleApiClient != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);

        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void gotomenu(View v) {
        Intent it = new Intent(this, menuActivity.class);

        startActivity(it);
    }

}
