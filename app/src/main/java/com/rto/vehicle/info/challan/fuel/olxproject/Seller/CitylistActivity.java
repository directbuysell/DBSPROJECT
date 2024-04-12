package com.rto.vehicle.info.challan.fuel.olxproject.Seller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.rto.vehicle.info.challan.fuel.olxproject.R;

public class CitylistActivity extends AppCompatActivity {
    Activity activity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citylist);
        activity = CitylistActivity.this;


    }



    @Override
    public void onBackPressed() {
        finish();
    }
}