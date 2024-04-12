package com.rto.vehicle.info.challan.fuel.olxproject.startapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.activity.HomeActivity;

public class TeamsActivity extends AppCompatActivity {
    Activity activity;
    LinearLayout ll_accept,ll_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        activity = TeamsActivity.this;


        ll_accept = (LinearLayout) findViewById(R.id.ll_accept);
        ll_cancel = (LinearLayout) findViewById(R.id.ll_cancel);


        ll_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, HomeActivity.class));
            }
        });

    }
}