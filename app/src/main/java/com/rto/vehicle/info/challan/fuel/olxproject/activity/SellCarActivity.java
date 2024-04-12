package com.rto.vehicle.info.challan.fuel.olxproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.Methods;

public class SellCarActivity extends AppCompatActivity {
    Activity activity;

    EditText etcarnumber;
    Button btnsell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_car);
        activity = SellCarActivity.this;
        GlobalAcesss.toolbar(activity,"Sell car");

        etcarnumber = (EditText) findViewById(R.id.etcarnumber);
        btnsell = (Button) findViewById(R.id.btnsell);


        btnsell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = etcarnumber.toString().trim();
                if (trim.isEmpty()) {
                    etcarnumber.setError("Please enter Income Tax number");
                    etcarnumber.requestFocus();
                } else if (!trim.matches(Methods.carnoPattern)) {
                    etcarnumber.setError("Please enter valid pan number");
                    etcarnumber.requestFocus();
                }else {
                    startActivity(new Intent(activity, Carinfo_activity.class));
                }


            }
        });


    }
}