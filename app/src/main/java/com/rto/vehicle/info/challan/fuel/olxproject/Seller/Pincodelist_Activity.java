package com.rto.vehicle.info.challan.fuel.olxproject.Seller;

import static android.content.ContentValues.TAG;
import static com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss.apiInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.adpter.CityPincodeAdapter;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.Methods;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.SharePrefs;
import com.rto.vehicle.info.challan.fuel.olxproject.model.PincodeModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pincodelist_Activity extends AppCompatActivity {
    Activity activity;

    RecyclerView rv_citylist;
    EditText etpincodeKeyword;

    private CityPincodeAdapter adapter;

    List<PincodeModel.DataItem> pincodedate;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pincodelist);
        activity = Pincodelist_Activity.this;
        searchCitiesPincode(SharePrefs.getCityid());
        Log.e("=====id",""+SharePrefs.getCityid());



        rv_citylist = (RecyclerView) findViewById(R.id.rv_citylist);
        etpincodeKeyword = (EditText) findViewById(R.id.etpincodeKeyword);










        etpincodeKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String obj = etpincodeKeyword.getText().toString();
                if (pincodedate == null) {
                    return;
                }
                adapter.getFilter().filter(editable.toString());
            }
        });


    }

    private void searchCitiesPincode(int query) {
        Methods.progressDialogShow(activity);
        Call<PincodeModel> call = apiInterface.searchCitiespinCode(query);
        call.enqueue(new Callback<PincodeModel>() {
            @Override
            public void onResponse(Call<PincodeModel> call, Response<PincodeModel> response) {
                if (response.isSuccessful()) {
                    PincodeModel cityModel = response.body();

                    Log.e("======@@City", "" + cityModel);

                    if (cityModel != null && cityModel.getData() != null) {
                        List<PincodeModel.DataItem> dataItems = cityModel.getData();
                        Log.e("======@@City1", "" + dataItems.size());
                        pincodedate = new ArrayList<>();
                        Log.e("======@@City2", "" + pincodedate.size());
                        for (PincodeModel.DataItem dataItem : dataItems) {
                            Log.e("======@@City3", "" + dataItems);
                            pincodedate.add(new PincodeModel.DataItem(dataItem.getPincode(), dataItem.getAreaId(), dataItem.getAreaName()));
                            Methods.progressDialogDismiss();
                        }

                        rv_citylist.setHasFixedSize(true);
                        rv_citylist.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, true));
                        adapter = new CityPincodeAdapter(activity, pincodedate);
                        rv_citylist.setAdapter(adapter);

                    }
                } else {
                    Log.e(TAG, "Failed to retrieve cities: " + response.message());
                    Methods.progressDialogDismiss();
                }
            }

            @Override
            public void onFailure(Call<PincodeModel> call, Throwable t) {
                Log.e(TAG, "Error fetching cities: " + t.getMessage());
            }
        });
    }
}