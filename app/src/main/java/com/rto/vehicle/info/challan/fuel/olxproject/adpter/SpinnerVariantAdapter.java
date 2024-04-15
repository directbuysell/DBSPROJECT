package com.rto.vehicle.info.challan.fuel.olxproject.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.VariantModel;

import java.util.List;

public class SpinnerVariantAdapter extends ArrayAdapter<String> {
    private List<VariantModel.DataItem> itemList;
    private FragmentActivity mContext;


    public SpinnerVariantAdapter(FragmentActivity context, int item_veriant, List<VariantModel.DataItem> variantItems) {
        super(context, item_veriant, variantItems);
        this.mContext = context;
        this.itemList = variantItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(android.R.layout.simple_spinner_item, null);
        }
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(itemList.get(position));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        }
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(itemList.get(position));
        return view;
    }
}