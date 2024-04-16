package com.rto.vehicle.info.challan.fuel.olxproject.Seller;

import static android.content.ContentValues.TAG;
import static com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss.apiInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.adpter.SpinnerVersionAdapter;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.Methods;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.SharePrefs;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.PriceRangeModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.VariantModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterSellinPrice_Activity extends AppCompatActivity {
    Activity activity;
    EditText etprice;
    Button btn_confirm;
    TextView tv_name;
    TextView tv_details;
    TextView tv_min;
    TextView tv_Max;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_sellin_price);
        activity = EnterSellinPrice_Activity.this;
        GlobalAcesss.toolbar(activity,"Sell Car");


        etprice = (EditText) findViewById(R.id.etprice);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_details = (TextView)findViewById(R.id.tv_details);

        tv_min = (TextView)findViewById(R.id.tv_min);
        tv_Max = (TextView)findViewById(R.id.tv_Max);


        tv_name.setText(SharePrefs.getRegistrationyear()+", "+SharePrefs.getCarModel_Name());
        tv_details.setText(SharePrefs.getCarvaeiant()+" ● "+SharePrefs.getOwnertype()+" ● "+SharePrefs.getkm()+" ● "+SharePrefs.getCityName());


        SellinPriceRange();

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

    private void SellinPriceRange() {

        Methods.progressDialogShow(activity);
         Call<PriceRangeModel> call = apiInterface.searchPriceRange(SharePrefs.getCityid(), SharePrefs.getcarModel_ID(),SharePrefs.getvaeiantID());
        call.enqueue(new Callback<PriceRangeModel>() {
            @Override
            public void onResponse(Call<PriceRangeModel> call, Response<PriceRangeModel> response) {
                if (response.isSuccessful()) {
                    PriceRangeModel variantModel = response.body();

                    Log.e("======@@City", "" + variantModel);

                    if (variantModel != null && variantModel.getData() != null) {


                        tv_min.setText("₹ " + GlobalAcesss.DecimalintformatAmount(variantModel.getData().getMinPrice()));
                        tv_Max.setText("₹ " + GlobalAcesss.DecimalintformatAmount(variantModel.getData().getMaxPrice()));


                        Methods.progressDialogDismiss();


                    }
                } else {
                    Log.e(TAG, "Failed to retrieve cities: " + response.message());
                    Methods.progressDialogDismiss();

                }
            }

            @Override
            public void onFailure(Call<PriceRangeModel> call, Throwable t) {
                Log.e(TAG, "Error fetching cities: " + t.getMessage());
            }
        });
    }


}