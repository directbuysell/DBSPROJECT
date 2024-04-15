package com.rto.vehicle.info.challan.fuel.olxproject.adpter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.Seller.Pincodelist_Activity;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.SharePrefs;
import com.rto.vehicle.info.challan.fuel.olxproject.model.CityModel;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {
    private List<CityModel.DataItem> cities;
    Activity activity;

    public CityAdapter(List<CityModel.DataItem> cities, Activity activity2) {
        this.cities = cities;
        activity = activity2;

    }

    public void updateData(List<CityModel.DataItem> cities) {
        this.cities.clear();
        this.cities.addAll(cities);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        CityModel.DataItem city = cities.get(position);
        holder.tv_city.setText(city.getCityName());
        holder.tv_state.setText(city.getStateName());
        holder.ll_mainlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePrefs.editor("cityname",city.getCityName());
                SharePrefs.editor("Cityid",city.getCityId());

                Intent intent = new Intent(activity, Pincodelist_Activity.class);
                activity.startActivity(intent);
                
            }
        });

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_city;
        public TextView tv_state;
        public LinearLayout ll_mainlist;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_city = itemView.findViewById(R.id.tv_city);
            tv_state = itemView.findViewById(R.id.tv_state);
            ll_mainlist = itemView.findViewById(R.id.ll_mainlist);
        }
    }
}