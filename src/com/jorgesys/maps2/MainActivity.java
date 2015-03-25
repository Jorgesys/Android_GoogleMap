package com.jorgesys.maps2;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends ActionBarActivity implements OnMapReadyCallback{
	
	private static final String MARKER_TITLE = "Google Maps by Jorgesys";
	private static final String MARKER_SNIPPET = "Hello from Iasi Romania!";
	private static final String MARKER_URL = "http://en.wikipedia.org/wiki/Ia%C8%99i";
	private SupportMapFragment map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    
        
        /**
         * Remember go to : https://console.developers.google.com enable "Google Maps Android API v2"
         * create a Key for Android applications
         * and add the "allowed Android Application". ACCEPT REQUESTS FROM AN ANDROID APPLICATION WITH ONE OF THE CERTIFICATE FINGERPRINTS AND PACKAGE NAMES LISTED BELOW
         * One SHA1 certificate fingerprint and package name (separated by a semicolon) per line. Example: 45:B5:E4:6F:36:AD:0A:98:94:B4:02:66:2B:12:17:F2:56:26:A0:E0;com.example.myexample
         *
         */
        
        map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);//remember getMap() is deprecated!      
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onMapReady(GoogleMap map) {
		   map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(47.17, 27.5699), 16)); //Iasi coordinates.
	        map.addMarker(new MarkerOptions()
	                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
	                .title(MARKER_TITLE)
	                .snippet(MARKER_SNIPPET)
	                .anchor(0.0f, 1.0f) 
	                .position(new LatLng(47.17, 27.5699))).showInfoWindow(); //Iasi, Romania
			map.setMyLocationEnabled(true);		
		
			map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {						
				@Override
				public void onInfoWindowClick(Marker marker) {
					   startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKER_URL)));
				}				
			});
			
	}
}
