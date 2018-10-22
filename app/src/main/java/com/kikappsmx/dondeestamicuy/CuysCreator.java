package com.kikappsmx.dondeestamicuy;

import android.os.Handler;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CuysCreator {

    private static DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    public static final String GEO_FIRE = "geoFire";
    public static final String CUYS = "cuys";
    public static final double MIN = -0.0005;
    public static final double MAX = 0.0005;

    public static void create() {
        for (int i = 0; i < 30; i++) {
            Cuy cuy = new Cuy();
            cuy.id = String.valueOf(i);
            updateLocation(cuy);
        }
    }

    private static void updateLocation(Cuy cuy) {
        reference.child(CUYS).child(cuy.id).setValue(cuy, (databaseError, databaseReference) -> {
            GeoFire geoFire = new GeoFire(reference.child(GEO_FIRE));
            geoFire.setLocation(cuy.id, new GeoLocation(cuy.latitude, cuy.longitude), (key, error) -> new Handler().postDelayed(() -> {
                cuy.latitude += MIN + Math.random() * (MAX - MIN);
                cuy.longitude += MIN + Math.random() * (MAX - MIN);
                updateLocation(cuy);
            }, 5000));
        });
    }
}
