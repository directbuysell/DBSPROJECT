package com.rto.vehicle.info.challan.fuel.olxproject.fragment;

import static android.content.ContentValues.TAG;
import static com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss.apiInterface;
import static java.lang.Integer.parseInt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.adpter.CarModelAdapter;
import com.rto.vehicle.info.challan.fuel.olxproject.adpter.CityPincodeAdapter;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.Methods;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.SharePrefs;
import com.rto.vehicle.info.challan.fuel.olxproject.model.CityModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.BrandModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.CarModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.PincodeModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.YearModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragmentmodel extends Fragment {


    RecyclerView rv_model;
    CarModelAdapter adapter;
    EditText et_modelKeyword;

    List<CarModel.DataItem> carmodel_Items ;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_fragmentmodel, container, false);


        rv_model = (RecyclerView) rootView.findViewById(R.id.rv_model);
        et_modelKeyword = (EditText)rootView.findViewById(R.id.et_modelKeyword);

        SearchCarModel(getActivity());
        et_modelKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String obj = et_modelKeyword.getText().toString();
                if (carmodel_Items == null) {
                    return;
                }
                adapter.getFilter().filter(editable.toString());
            }
        });



        return rootView;
    }

    private void SearchCarModel(FragmentActivity activity) {

        Log.e("====NUmber",""+SharePrefs.getcarbrand()+"======>"+SharePrefs.getRegistrationyear());

        Methods.progressDialogShow(activity);

        Call<CarModel> call = apiInterface.searchcarModel(SharePrefs.getcarbrand(),SharePrefs.getRegistrationyear());
        call.enqueue(new Callback<CarModel>() {
            @Override
            public void onResponse(Call<CarModel> call, Response<CarModel> response) {
                if (response.isSuccessful()) {
                    CarModel carModel = response.body();

                    Log.e("======@@City", "" + carModel.getData());

                    if (carModel != null && carModel.getData() != null) {


                        List<CarModel.DataItem> dataItems = carModel.getData();
                        Log.e("======@@City1", "" + dataItems.size());
                        carmodel_Items = new ArrayList<>();
                        Log.e("======@@City2", "" + carmodel_Items.size());
                        for (CarModel.DataItem dataItem : dataItems) {
                            Log.e("======@@City3", "" + dataItems);
                            carmodel_Items.add(new CarModel.DataItem(dataItem.getModelId(), dataItem.getModelName()));
//                            pincodedate.add(new PincodeModel.DataItem(dataItem.getPincode(), dataItem.getAreaId(), dataItem.getAreaName()));
                            Methods.progressDialogDismiss();
                        }




//                        for (CarModel.DataItem dataItem : carmodel_Items) {
//                            Log.e("======@@City3",""+carmodel_Items.size());
//                            carmodel_Items.add(new CarModel.DataItem(dataItem.getModelId(), dataItem.getModelName()));
//
//                        }

                        rv_model.setHasFixedSize(true);
                        rv_model.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, true));
                        adapter = new CarModelAdapter(activity, carmodel_Items);
                        rv_model.setAdapter(adapter);
                        Methods.progressDialogDismiss();




                    }
                } else {
                    Log.e(TAG, "Failed to retrieve cities: " + response.message());
                    Methods.progressDialogDismiss();

                }
            }

            @Override
            public void onFailure(Call<CarModel> call, Throwable t) {
                Log.e(TAG, "Error fetching cities: " + t.getMessage());
            }
        });

    }
}