package com.rto.vehicle.info.challan.fuel.dbsproject.adpter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.rto.vehicle.info.challan.fuel.dbsproject.ItemClick.OnItemClickListener;
import com.rto.vehicle.info.challan.fuel.dbsproject.R;
import com.rto.vehicle.info.challan.fuel.dbsproject.comman.SharePrefs;
import com.rto.vehicle.info.challan.fuel.dbsproject.model.Other.BrandModel;

import java.util.List;

public class MakeAdapter extends RecyclerView.Adapter<MakeAdapter.ViewHolder> {

    private List<BrandModel.MakesItem> makes;
    FragmentActivity activity;
    private LayoutInflater mInflater;

    public static int selectedPos = RecyclerView.NO_POSITION;

    public MakeAdapter(FragmentActivity activity2, List<BrandModel.MakesItem> makes) {
        this.makes = makes;
        activity = activity2;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.make_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BrandModel.MakesItem make = makes.get(position);
        holder.makeName.setText(make.getCompName());


        holder.ll_brands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPos = position;
                notifyDataSetChanged();

            }
        });

     if (selectedPos == position) {
            holder.ll_brands.setBackground(activity.getResources().getDrawable(R.drawable.ic_box_theme));
            holder.makeName.setTextColor(activity.getResources().getColor(R.color.white));
            SharePrefs.editor("carbrand", make.getCompId());
         Toast.makeText(activity, "Clicked: " + make.getCompName(), Toast.LENGTH_SHORT).show();
            return;
        }
            holder.ll_brands.setBackground(activity.getResources().getDrawable(R.drawable.ic_box_null));
            holder.makeName.setTextColor(activity.getResources().getColor(R.color.text_gray));



    }

    @Override
    public int getItemCount() {
        return makes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView makeName;
        public LinearLayout ll_brands;

        public ViewHolder(View itemView) {
            super(itemView);
            makeName = itemView.findViewById(R.id.make_name);
            ll_brands = itemView.findViewById(R.id.ll_brands);
        }
    }


}