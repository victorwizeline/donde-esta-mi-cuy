package com.kikappsmx.dondeestamicuy;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private List<Marker> markers = new ArrayList<>();
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng devFestLocation = new LatLng(Cuy.LATITUDE, Cuy.LONGITUDE);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(devFestLocation, 17));
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CircleOptions circleOptions = new CircleOptions()
                .fillColor(ContextCompat.getColor(this, R.color.radiusColor))
                .center(devFestLocation)
                .strokeWidth(0)
                .radius(100);
        googleMap.addCircle(circleOptions);

        addMarkers(devFestLocation);
        updateMarkers();
    }

    private void addMarkers(LatLng latLng) {
        for (int i = 0; i < 30; i++) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .draggable(false)
                    .position(latLng)
                    .title(String.valueOf(i))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon));
            Marker marker = googleMap.addMarker(markerOptions);
            markers.add(marker);
        }
    }

    private void updateMarkers() {
        GeoFire geoFire = new GeoFire(reference.child(CuysCreator.GEO_FIRE));
        GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(Cuy.LATITUDE, Cuy.LONGITUDE), 0.1);
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String id, GeoLocation location) {
                markers.get(Integer.parseInt(id)).setVisible(true);
            }

            @Override
            public void onKeyExited(String id) {
                markers.get(Integer.parseInt(id)).setVisible(false);
            }

            @Override
            public void onKeyMoved(String id, GeoLocation location) {
                markers.get(Integer.parseInt(id)).setPosition(new LatLng(location.latitude, location.longitude));
            }

            @Override
            public void onGeoQueryReady() {

            }

            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });
    }
}
