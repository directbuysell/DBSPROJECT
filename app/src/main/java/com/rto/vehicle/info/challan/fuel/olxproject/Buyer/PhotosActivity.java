package com.rto.vehicle.info.challan.fuel.olxproject.Buyer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.Seller.UploadGuide_Activity;
import com.rto.vehicle.info.challan.fuel.olxproject.adpter.CarPhotoAdapter;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss;

public class PhotosActivity extends AppCompatActivity {
    Activity activity;
    RecyclerView rv_photo;
    CarPhotoAdapter mAdapter;
    int[] images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        activity = PhotosActivity.this;
        GlobalAcesss.toolbar(activity, "Used Car");

        rv_photo = (RecyclerView) findViewById(R.id.rv_photo);

        Intent intent = getIntent();
        if (intent != null) {
            images = intent.getIntArrayExtra("imageArray");
            if (images != null) {

                rv_photo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
                mAdapter = new CarPhotoAdapter(activity, images);
                rv_photo.setAdapter(mAdapter);

            }
        }





    }
}