package com.rto.vehicle.info.challan.fuel.olxproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import android.app.Activity;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.adpter.CarinfoPagerAdapter;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss;

public class Carinfo_activity extends AppCompatActivity {
    Activity activity;
    CarinfoPagerAdapter carinfoPagerAdapter;
    ViewPager2 viewpager;
    TabLayout tab_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carinfo);
        activity = Carinfo_activity.this;
        GlobalAcesss.toolbar(activity, "Sell car");

        viewpager = findViewById(R.id.viewpager);
        tab_layout = findViewById(R.id.tab_layout);


//        carinfoPagerAdapter = new CarinfoPagerAdapter(getSupportFragmentManager());
//        viewpager.setAdapter(carinfoPagerAdapter);
//        tab_layout.setupWithViewPager(viewpager);







    }



}