package com.rto.vehicle.info.challan.fuel.olxproject.Seller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss;

public class UploadGuide_Activity extends AppCompatActivity {
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_guide);
        activity = UploadGuide_Activity.this;
        GlobalAcesss.toolbar(activity,"Photo Upload Tips");
    }
}