package com.rto.vehicle.info.challan.fuel.olxproject.adpter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.rto.vehicle.info.challan.fuel.olxproject.fragment.FragmentBrand;
import com.rto.vehicle.info.challan.fuel.olxproject.fragment.Fragmentlocation;
import com.rto.vehicle.info.challan.fuel.olxproject.fragment.Fragmentmodel;
import com.rto.vehicle.info.challan.fuel.olxproject.fragment.Fragmentodometer;
import com.rto.vehicle.info.challan.fuel.olxproject.fragment.Fragmentowner;
import com.rto.vehicle.info.challan.fuel.olxproject.fragment.Fragmentvariant;
import com.rto.vehicle.info.challan.fuel.olxproject.fragment.Fragmentyear;

public class CarinfoPagerAdapter extends FragmentStateAdapter {

    private final Context context;

    public CarinfoPagerAdapter(@NonNull FragmentActivity fragmentActivity, Context context) {
        super(fragmentActivity);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new FragmentBrand();
        } else if (position == 1) {
            return new Fragmentyear();
        } else if (position == 2) {
            return new Fragmentmodel();
        } else if (position == 3) {
            return new Fragmentvariant();
        } else if (position == 4) {
            return new Fragmentowner();
        } else if (position == 5) {
            return new Fragmentodometer();
        } else if (position == 6) {
            return new Fragmentlocation();
        } else {
            return new FragmentBrand(); // Default case
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}