package com.rto.vehicle.info.challan.fuel.olxproject.adpter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rto.vehicle.info.challan.fuel.olxproject.R;

import java.util.List;

public class OwnerAdapter extends ArrayAdapter<String> {
    private int selectedItem = -1;

    public OwnerAdapter(Context context, List<String> items) {
        super(context, 0, items);
    }

    public void setSelectedItem(int position) {
        selectedItem = position;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_owner, parent, false);
        }

        TextView tv_ownername = convertView.findViewById(R.id.tv_ownername);
        tv_ownername.setText(item);

        if (position == selectedItem) {
            convertView.setBackgroundColor(Color.parseColor("#002F34")); // Set background color of selected item
            tv_ownername.setTextColor(Color.WHITE); // Set text color of selected item
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT); // Reset background color of other items
            tv_ownername.setTextColor(Color.parseColor("#616161")); // Reset text color of other items
        }

        return convertView;
    }
}