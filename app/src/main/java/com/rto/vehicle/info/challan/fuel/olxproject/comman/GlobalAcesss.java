package com.rto.vehicle.info.challan.fuel.olxproject.comman;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.multidex.MultiDexApplication;


import com.rto.vehicle.info.challan.fuel.olxproject.R;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class GlobalAcesss extends MultiDexApplication {

    public static GlobalAcesss context;




    public void onCreate() {
        super.onCreate();
        context = this;
        SharePrefs.init();



    }

    public static GlobalAcesss getContext() {
        return context;
    }


    public static final void backwardAnim(Activity activity) {
        activity.overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    public static final void forwardAnim(Activity activity) {
        activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public static final void backwardAnim(FragmentActivity activity) {
        activity.overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    public static final void forwardAnim(FragmentActivity activity) {
        activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public static void toolbar(final Activity activity, String str) {
        ImageView backImageView = (ImageView) activity.findViewById(R.id.backImageView);
        TextView tv_title = (TextView)activity.findViewById(R.id.tv_title);
        tv_title.setText(str);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

    public static String formatAmount(double d) {
        return String.format("%.2f", new Object[]{Double.valueOf(d)});
    }

    public static void showToast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }












}
