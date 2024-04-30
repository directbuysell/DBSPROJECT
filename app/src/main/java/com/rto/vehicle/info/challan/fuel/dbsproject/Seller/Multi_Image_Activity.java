package com.rto.vehicle.info.challan.fuel.dbsproject.Seller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.rto.vehicle.info.challan.fuel.dbsproject.CropImage.CropImage;
import com.rto.vehicle.info.challan.fuel.dbsproject.R;
import com.rto.vehicle.info.challan.fuel.dbsproject.adpter.ImageAdapter;
import com.rto.vehicle.info.challan.fuel.dbsproject.comman.AspectRatioLayout;
import com.rto.vehicle.info.challan.fuel.dbsproject.comman.Methods;
import com.rto.vehicle.info.challan.fuel.dbsproject.comman.SharePrefs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Multi_Image_Activity extends AppCompatActivity {
    Activity activity;
    ImageView img;

    public static AspectRatioLayout asp_img;
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    public static Button btn_done;
    Uri uri;
    Intent intent;
    private List<String> bitmapList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_image);
        activity = Multi_Image_Activity.this;

        img = (ImageView) findViewById(R.id.img);
        recyclerView = findViewById(R.id.recyclerView);
        btn_done = findViewById(R.id.btn_done);
        asp_img = findViewById(R.id.asp_img);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        imageAdapter = new ImageAdapter(selectedImages);
//        recyclerView.setAdapter(imageAdapter);




        imageAdapter = new ImageAdapter(bitmapList);


        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(imageAdapter);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSelectFrom();
            }
        });


//        if(ImageAdapter.imageList.size() == 0){
//            Multi_Image_Activity.btn_done.setVisibility(View.GONE);
//        }else {
//            Multi_Image_Activity.btn_done.setVisibility(View.VISIBLE);
//        }

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (bitmapList.size() != 0) {
//                    asp_img.setVisibility(View.GONE);
//                }else
                if (bitmapList.size() != 6) {
                    dialogSelectFrom();
                    btn_done.setText("Upload");

                } else {

                    SharePrefs.editor("isStep2Done", true);
                    startActivity(new Intent(activity, SellCarSetp_Activity.class));
                }
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
                        bottomSheetDialog.dismiss();
                        File file = new File(Methods.getDirectory(".Temp") + System.currentTimeMillis() + ".jpg");
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Context applicationContext = activity.getApplicationContext();
                        uri = FileProvider.getUriForFile(applicationContext, getApplicationContext().getPackageName() + ".provider", file);
                        intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        intent.putExtra("output", uri);
                        startActivityForResult(intent, Methods.RC_CAMERA);

                    }
                });

                ll_Gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Images"), Methods.RC_GALLERY);
                    }
                });


            }
        });
    }


    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);


        if (i == Methods.RC_CAMERA && i2 == -1) {
            CropImage.activity(uri).setAspectRatio(16, 9).start(activity);
        }
        if (i == Methods.RC_GALLERY && i2 == -1) {
            CropImage.activity(intent.getData()).setAspectRatio(16, 9).start(activity);
        }
        if (i == 203) {
            CropImage.ActivityResult activityResult = CropImage.getActivityResult(intent);
            if (i2 == -1) {
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), activityResult.getUri());

                } catch (IOException e) {
                    e.printStackTrace();
                }


                saveToPref(bitmap);


            } else if (i2 == 204) {
                activityResult.getError();
            }
        }
    }

    public void saveToPref(Bitmap bitmap) {


        File file = new File(Methods.getDirectory(".Temp") + System.currentTimeMillis() + ".jpg");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.e("====coverpick12", "" + SharePrefs.getcoverurl());

        try (FileOutputStream fos = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Log.d("===TAG", "Image saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("==TAG", "Failed to save image");
        }
        Log.e("======filelist", "" + file.toString());
        imageAdapter.addImage(file.toString());





    }


}