package com.rto.vehicle.info.challan.fuel.olxproject.startapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.SharePrefs;

public class LaunchingActivity extends AppCompatActivity {

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_launching);
        activity = LaunchingActivity.this;
        NextScreen(2500);

    }

    private void NextScreen(long l) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                openNextScreen();
            }
        }, l);
    }

    public void openNextScreen() {
        if (!SharePrefs.getlogin()) {
            startActivity(new Intent(activity, LoginActivity.class));
            finish();

        } else {
//            startActivity(new Intent(activity, StartActivity.class));
//            finish();
        }

    }
}