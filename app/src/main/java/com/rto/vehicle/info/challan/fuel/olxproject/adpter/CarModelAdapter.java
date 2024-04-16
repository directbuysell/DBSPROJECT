package com.rto.vehicle.info.challan.fuel.olxproject.adpter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.Seller.SellCarActivity;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.SharePrefs;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.CarModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.PincodeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CarModelAdapter extends RecyclerView.Adapter<CarModelAdapter.ViewHolder> implements Filterable {

    private List<CarModel.DataItem> dataList;
    private List<CarModel.DataItem> dataListFull;

    Activity activity;

    int selectedPos = 0;



    public CarModelAdapter(Activity activity2, List<CarModel.DataItem> pincodedate) {
        activity = activity2;
        this.dataList = pincodedate;
        dataListFull = new ArrayList<>(dataList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carmodel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CarModel.DataItem item = dataList.get(position);
        holder.tv_make_name.setText(item.getModelName());

        holder.ll_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPos = position;
                notifyDataSetChanged();
            }
        });
//
        if (selectedPos == position) {
            holder.ll_model.setBackground(activity.getResources().getDrawable(R.drawable.ic_box_theme));
            holder.tv_make_name.setTextColor(activity.getResources().getColor(R.color.white));
            SharePrefs.editor("carmodel_ID", item.getModelId());
            SharePrefs.editor("carmodel_name", item.getModelName());
            SharePrefs.update();
            return;
        }
        holder.ll_model.setBackground(activity.getResources().getDrawable(R.drawable.ic_box_null));
        holder.tv_make_name.setTextColor(activity.getResources().getColor(R.color.text_gray));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_make_name;
        LinearLayout ll_model;

        ViewHolder(View itemView) {
            super(itemView);
            tv_make_name = itemView.findViewById(R.id.tv_make_name);
            ll_model = itemView.findViewById(R.id.ll_model);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<CarModel.DataItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CarModel.DataItem item : dataListFull) {

                    if (item.getModelName().toLowerCase(Locale.US).contains(filterPattern) ) {
                        filteredList.add(item);
                    }



                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataList.clear();
            dataList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}