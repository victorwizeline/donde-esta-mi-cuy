package com.kikappsmx.dondeestamicuy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startMapsActivity();
    }

    private void startMapsActivity() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MapsActivity.class));
            finishAffinity();
        }, 1000);
    }
}
