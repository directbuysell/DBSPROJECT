package com.rto.vehicle.info.challan.fuel.olxproject.adpter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.SharePrefs;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.BrandModel;

import java.util.ArrayList;
import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

//    private static final int VIEW_TYPE_GROUP = 1;
//    private static final int VIEW_TYPE_MAKE = 2;
    public int selectedPos = 0;
//    private List<BrandModel.DataItem> dataItemList;
    private List<BrandModel.MakesItem> allMakesList;
    private List<BrandModel.MakesItem> filteredMakesList = new ArrayList<>();
    FragmentActivity activity;


    public BrandAdapter(FragmentActivity activity2, List<BrandModel.DataItem> dataItemList, List<BrandModel.MakesItem> allMakesList) {
//        this.dataItemList = dataItemList;
        this.allMakesList = allMakesList;
        this.filteredMakesList.addAll(allMakesList);
        activity = activity2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
//        if (viewType == VIEW_TYPE_GROUP) {
//            view = inflater.inflate(R.layout.item_group, parent, false);
//            return new GroupViewHolder(view);
//        } else {
        view = inflater.inflate(R.layout.item_make, parent, false);
        return new MakeViewHolder(view);
//        }
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {


        final BrandModel.MakesItem Makesdata = allMakesList.get(position);

//        if (itemType == VIEW_TYPE_GROUP) {
//            final BrandModel.DataItem data = dataItemList.get(position);
//            final GroupViewHolder holder = (GroupViewHolder) viewHolder;
//
//            holder.groupNameTextView.setText(data.getGroupName());
//
//        } else {

        MakeViewHolder holder22 = (MakeViewHolder) viewHolder;
        holder22.makeNameTextView.setText(Makesdata.getCompName());
        holder22.ll_brands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPos = position;
                notifyDataSetChanged();

            }
        });

        if (selectedPos == position) {
            holder22.ll_brands.setBackground(activity.getResources().getDrawable(R.drawable.ic_box_theme));
            holder22.makeNameTextView.setTextColor(activity.getResources().getColor(R.color.white));
            SharePrefs.editor("carbrand", Makesdata.getCompId());
            return;
        }
        holder22.ll_brands.setBackground(activity.getResources().getDrawable(R.drawable.ic_box_null));
        holder22.makeNameTextView.setTextColor(activity.getResources().getColor(R.color.text_gray));


//        }
    }


    @Override
    public int getItemCount() {
        return allMakesList.size() + filteredMakesList.size();
    }

//    @Override
//    public int getItemViewType(int position) {
//        return position < allMakesList.size() ;
//    }

    public void filterMakes(String query) {
        filteredMakesList.clear();
        if (query.isEmpty()) {
            filteredMakesList.addAll(allMakesList);
        } else {
            query = query.toLowerCase();
            for (BrandModel.MakesItem make : allMakesList) {
                if (make.getCompName().toLowerCase().contains(query)) {
                    filteredMakesList.add(make);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void updateData(List<BrandModel.DataItem> dataItems, List<BrandModel.MakesItem> makesItems) {
    }

//    static class GroupViewHolder extends RecyclerView.ViewHolder {
//        private TextView groupNameTextView;
//
//        GroupViewHolder(@NonNull View itemView) {
//            super(itemView);
//            groupNameTextView = itemView.findViewById(R.id.tv_group_name);
//        }
//
//
//    }




    public class MakeViewHolder extends RecyclerView.ViewHolder {
        public TextView makeNameTextView;
        LinearLayout ll_brands;

        public MakeViewHolder(View itemView) {
            super(itemView);
            makeNameTextView = itemView.findViewById(R.id.tv_make_name);
            ll_brands = itemView.findViewById(R.id.ll_brands);
        }


    }


}