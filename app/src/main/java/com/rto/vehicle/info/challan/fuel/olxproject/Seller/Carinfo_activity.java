package com.rto.vehicle.info.challan.fuel.olxproject.Seller;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.adpter.CarinfoPagerAdapter;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss;

public class Carinfo_activity extends AppCompatActivity {
    Activity activity;

    ViewPager2 mViewPager;

    TabLayout pageIndicator;

    Button btnNext,btnBack;
    TextView tv_step;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carinfo);
        activity = Carinfo_activity.this;
        GlobalAcesss.toolbar(activity, "Sell car");

        mViewPager = findViewById(R.id.viewPager);
        tv_step = findViewById(R.id.tv_step);
//        pageIndicator = findViewById(R.id.pageIndicator);
        btnNext = findViewById(R.id.btn_next_step);
        btnBack = findViewById(R.id.btn_previous_step);



        mViewPager.setAdapter(new CarinfoPagerAdapter(this, this));
//        mViewPager.setOffscreenPageLimit(1);



        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tv_step.setText("Step - "+(position+1)+"/7");
                if (position == 6) {
                    btnNext.setText(getResources().getString(R.string.next));
                    startActivity(new Intent(activity,EnterSellinPrice_Activity.class));
                } else {
                    btnNext.setText(getResources().getString(R.string.next));
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}

            @Override
            public void onPageScrollStateChanged(int arg0) {}
        });

//        new TabLayoutMediator(pageIndicator, mViewPager, (tab, position) -> {
//        }).attach();

        btnNext.setOnClickListener(v -> {
            if (getItem() > 7) {
                finish();
            } else {
                mViewPager.setCurrentItem(getItem() + 1, true);
//
            }
        });

        btnBack.setOnClickListener(v -> {
            if (getItem() == 0) {
                finish();
            } else {
                mViewPager.setCurrentItem(getItem() - 1, true);
//                tv_step.setText("Step-"+ (getItem()-1) + "/7");
            }
        });



    }

    private int getItem() {
        return mViewPager.getCurrentItem();
    }



}