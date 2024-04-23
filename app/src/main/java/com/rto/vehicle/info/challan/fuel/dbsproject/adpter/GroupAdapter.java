package com.rto.vehicle.info.challan.fuel.dbsproject.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rto.vehicle.info.challan.fuel.dbsproject.ItemClick.OnItemClickListener;
import com.rto.vehicle.info.challan.fuel.dbsproject.R;
import com.rto.vehicle.info.challan.fuel.dbsproject.model.Other.BrandModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> implements Filterable {

    private List<BrandModel.DataItem> groupList;
    private List<BrandModel.DataItem> groupListFiltered;


    private LayoutInflater mInflater;
    FragmentActivity activity;



    public GroupAdapter(FragmentActivity activity2, List<BrandModel.DataItem> groupList) {
        this.groupList = groupList;
        this.groupListFiltered = groupList;
        activity = activity2;


    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BrandModel.DataItem group = groupListFiltered.get(position);
        holder.groupName.setText(group.getGroupName());

        MakeAdapter adapter = new MakeAdapter(activity,group.getMakes());
        holder.makesRecyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return groupListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    groupListFiltered = groupList;
                } else {
                    List<BrandModel.DataItem> filteredList = new ArrayList<>();
                    for (BrandModel.DataItem group : groupList) {
                        List<BrandModel.MakesItem> filteredMakes = new ArrayList<>();
                        for (BrandModel.MakesItem make : group.getMakes()) {
                            if (make.getCompName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredMakes.add(make);
                            }
                        }
                        if (!filteredMakes.isEmpty()) {
                            BrandModel.DataItem filteredGroup = new BrandModel.DataItem();
                            filteredGroup.setGroupName(group.getGroupName());
                            filteredGroup.setMakes(filteredMakes);
                            filteredList.add(filteredGroup);
                        }
                    }
                    groupListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = groupListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                groupListFiltered = (ArrayList<BrandModel.DataItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView groupName;
        public RecyclerView makesRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.group_name);
            makesRecyclerView = itemView.findViewById(R.id.makes_recycler_view);
            makesRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false));
        }
    }
}