package com.rto.vehicle.info.challan.fuel.olxproject.adpter;

import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.ColourModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.VariantModel;

import java.util.List;

public class SpinnerColourAdapter extends ArrayAdapter<ColourModel.DataItem> {
    public SpinnerColourAdapter(FragmentActivity activity, List<ColourModel.DataItem> colourItems) {
        super(activity,0, colourItems);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_colour, parent, false
            );
        }

        ImageView iv_colour = convertView.findViewById(R.id.iv_colour);
        TextView tv_colourname = convertView.findViewById(R.id.tv_colourname);


        ColourModel.DataItem currentItem = getItem(position);

        if (currentItem != null) {
            try {
                iv_colour.setBackgroundColor(Color.parseColor("#"+currentItem.getValue()));
            } catch (Exception e) {

            }
            tv_colourname.setText(currentItem.getDisplayName());

        }

        return convertView;
    }
}