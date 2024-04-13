package com.rto.vehicle.info.challan.fuel.olxproject.Seller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.Methods;

public class Uploadcarimage_Activity extends AppCompatActivity {
    Activity activity;
    ImageView img;
    TextView tv_tips;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadcarimage);
        activity = Uploadcarimage_Activity.this;
        GlobalAcesss.toolbar(activity,"Upload Image");
        img = (ImageView) findViewById(R.id.img);
        tv_tips = (TextView)findViewById(R.id.tv_tips);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSelectFrom();
            }
        });

        tv_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, UploadGuide_Activity.class));
            }
        });



    }

    private void dialogSelectFrom() {
        Methods.bottomSheetDialog(activity, R.layout.bottom_upload, new Methods.BottomSheetDialogListener() {
            @Override
            public void onCreated(BottomSheetDialog bottomSheetDialog) {

                LinearLayout ll_Camera = (LinearLayout) bottomSheetDialog.findViewById(R.id.ll_Camera);
                LinearLayout ll_Gallery = (LinearLayout) bottomSheetDialog.findViewById(R.id.ll_Gallery);


                ll_Camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                ll_Gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });


            }
        });
    }
}