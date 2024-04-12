package com.rto.vehicle.info.challan.fuel.olxproject.Seller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss;

public class EnterSellinPrice_Activity extends AppCompatActivity {
    Activity activity;
    EditText etprice;
    Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_sellin_price);
        activity = EnterSellinPrice_Activity.this;
        GlobalAcesss.toolbar(activity,"Sell Car");


        etprice = (EditText) findViewById(R.id.etprice);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);


        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String trim = etprice.getText().toString().trim();
                if (trim.isEmpty()) {
                    etprice.setError("Please enter Selling Price");
                    etprice.requestFocus();
                    return;
                } else {
                    startActivity(new Intent(activity, Uploadcarimage_Activity.class));
                }

            }
        });


    }
}