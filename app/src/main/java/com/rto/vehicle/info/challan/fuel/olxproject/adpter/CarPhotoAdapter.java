package com.rto.vehicle.info.challan.fuel.olxproject.adpter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rto.vehicle.info.challan.fuel.olxproject.R;

public class CarPhotoAdapter extends RecyclerView.Adapter<CarPhotoAdapter.MyViewHolder> {
    private int[] mData;
    private Activity activity;

    public CarPhotoAdapter(Activity activity2, int[] data) {
        mData = data;
        activity = activity2;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carimage_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // Bind data to views in ViewHolder
        int imageResourceId = mData[position];
        Glide.with(activity).load(imageResourceId).centerCrop().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img); // Replace with your image view id
        }
    }
}