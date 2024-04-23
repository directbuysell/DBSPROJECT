package com.rto.vehicle.info.challan.fuel.dbsproject.adpter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.rto.vehicle.info.challan.fuel.dbsproject.fragment.FragmentBrand;
import com.rto.vehicle.info.challan.fuel.dbsproject.fragment.Fragmentlocation;
import com.rto.vehicle.info.challan.fuel.dbsproject.fragment.Fragmentmodel;
import com.rto.vehicle.info.challan.fuel.dbsproject.fragment.Fragmentodometer;
import com.rto.vehicle.info.challan.fuel.dbsproject.fragment.Fragmentowner;
import com.rto.vehicle.info.challan.fuel.dbsproject.fragment.Fragmentvariant;
import com.rto.vehicle.info.challan.fuel.dbsproject.fragment.Fragmentyear;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {

    private Fragment[] fragments;

    public ScreenSlidePagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        initializeFragments();
    }

    private void initializeFragments() {
        fragments = new Fragment[7]; // Number of fragments can be adjusted
        fragments[0] = Fragmentlocation.newInstance(0);
        fragments[0] = FragmentBrand.newInstance(1);
        fragments[0] = Fragmentyear.newInstance(2);
        fragments[0] = Fragmentmodel.newInstance(3);
        fragments[0] = Fragmentvariant.newInstance(4);
        fragments[0] = Fragmentowner.newInstance(5);
        fragments[0] = Fragmentodometer.newInstance(6);
        for (int i = 1; i < fragments.length; i++) {
            fragments[i] = Fragmentlocation.newInstance(i);
        }




    }

    @Override
    public Fragment createFragment(int position) {

        if (position == 0) {
            return new Fragmentlocation();

        } else if (position == 1) {
            return new FragmentBrand();

        } else if (position == 2) {
            return new Fragmentyear();

        } else if (position == 3) {
            return new Fragmentmodel();

        } else if (position == 4) {
            return new Fragmentvariant();

        } else if (position == 5) {
            return new Fragmentowner();

        } else if (position == 6) {
            return new Fragmentodometer();
        } else {
            return new Fragmentlocation(); // Default case
        }


//        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }

    // Public method to unlock the next fragment
    public void unlockNextFragment(int currentPosition) {
        if (currentPosition + 1 < fragments.length) {
            notifyItemChanged(currentPosition + 1);
        }
    }
}