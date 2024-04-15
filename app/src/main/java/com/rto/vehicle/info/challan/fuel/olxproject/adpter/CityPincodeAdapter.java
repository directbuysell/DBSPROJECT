package com.rto.vehicle.info.challan.fuel.olxproject.adpter;

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
import com.rto.vehicle.info.challan.fuel.olxproject.Seller.Pincodelist_Activity;
import com.rto.vehicle.info.challan.fuel.olxproject.Seller.SellCarActivity;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.SharePrefs;
import com.rto.vehicle.info.challan.fuel.olxproject.model.PincodeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CityPincodeAdapter extends RecyclerView.Adapter<CityPincodeAdapter.ViewHolder> implements Filterable {

    private List<PincodeModel.DataItem> dataList;
    private List<PincodeModel.DataItem> dataListFull;

    Activity activity;





    public CityPincodeAdapter(Activity activity2, List<PincodeModel.DataItem> pincodedate) {
        activity = activity2;
        this.dataList = pincodedate;
        dataListFull = new ArrayList<>(dataList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pincode, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PincodeModel.DataItem item = dataList.get(position);
        holder.tv_pincode.setText(item.getPincode());
        holder.tv_area.setText(item.getAreaName());
        holder.ll_mainlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePrefs.editor("cityPincode",item.getPincode());
               activity.startActivity(new Intent(activity, SellCarActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_pincode,tv_area;
        LinearLayout ll_mainlist;

        ViewHolder(View itemView) {
            super(itemView);
            tv_pincode = itemView.findViewById(R.id.tv_pincode);
            tv_area = itemView.findViewById(R.id.tv_area);
            ll_mainlist = itemView.findViewById(R.id.ll_mainlist);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<PincodeModel.DataItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (PincodeModel.DataItem item : dataListFull) {

                    if (item.getAreaName().toLowerCase(Locale.US).contains(filterPattern) || item.getPincode().toLowerCase().contains(filterPattern)) {
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