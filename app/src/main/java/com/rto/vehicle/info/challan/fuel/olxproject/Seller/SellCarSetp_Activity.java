package com.rto.vehicle.info.challan.fuel.olxproject.Seller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.rto.vehicle.info.challan.fuel.olxproject.R;

public class SellCarSetp_Activity extends AppCompatActivity {
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_car_setp);
        activity = SellCarSetp_Activity.this;
    }
}