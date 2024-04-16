package com.rto.vehicle.info.challan.fuel.olxproject.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.VariantModel;
import java.util.List;

public class SpinnerVersionAdapter extends ArrayAdapter<VariantModel.DataItem> {
    public SpinnerVersionAdapter(FragmentActivity activity, List<VariantModel.DataItem> variantItems) {
        super(activity, 0,variantItems);
    }

//    public SpinnerAdapter(Context context, ArrayList<VariantModel.DataItem> countryList) {
//        super(context, 0, countryList);
//    }

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
                    R.layout.item_veriant, parent, false
            );
        }

//        ImageView imageViewFlag = convertView.findViewById(R.id.image_view_flag);
        TextView tv_modelname = convertView.findViewById(R.id.tv_modelname);
        TextView tv_FuelType = convertView.findViewById(R.id.tv_FuelType);
        TextView tv_Type = convertView.findViewById(R.id.tv_Type);

        VariantModel.DataItem currentItem = getItem(position);

        if (currentItem != null) {
//            imageViewFlag.setImageResource(currentItem.getFlagImage());
            tv_modelname.setText(currentItem.getName());
            tv_FuelType.setText(currentItem.getFuelType());
            tv_Type.setText(currentItem.getTransmission());
        }

        return convertView;
    }
}