package com.rto.vehicle.info.challan.fuel.olxproject.fragment;

import static android.content.ContentValues.TAG;
import static com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss.apiInterface;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.adpter.CityAdapter;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.Methods;
import com.rto.vehicle.info.challan.fuel.olxproject.model.CityModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragmentlocation extends Fragment {

    EditText etSearchKeyword;
    RecyclerView rv_citylist;
    CityAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragmentlocation, container, false);

        etSearchKeyword = (EditText) rootView.findViewById(R.id.etSearchKeyword);
        rv_citylist = (RecyclerView) rootView.findViewById(R.id.rv_citylist);
        etSearchKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count >= 2) {
                    searchCities(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

//        searchCities("sur");
        rv_citylist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
        adapter = new CityAdapter(new ArrayList<>(), getActivity());
        rv_citylist.setAdapter(adapter);


        return rootView;
    }

    private void searchCities(String query) {
//        Methods.progressDialogShow(activity);
        Call<CityModel> call = apiInterface.searchCities(query);
        call.enqueue(new Callback<CityModel>() {
            @Override
            public void onResponse(Call<CityModel> call, Response<CityModel> response) {
                if (response.isSuccessful()) {
                    CityModel cityModel = response.body();

                    Log.e("======@@City", "" + cityModel);

                    if (cityModel != null && cityModel.getData() != null) {
                        List<CityModel.DataItem> dataItems = cityModel.getData();
                        Log.e("======@@City1", "" + dataItems.size());
                        List<CityModel.DataItem> cities = new ArrayList<>();
                        Log.e("======@@City2", "" + cities.size());
                        for (CityModel.DataItem dataItem : dataItems) {
                            Log.e("======@@City3", "" + dataItems);
                            cities.add(new CityModel.DataItem(dataItem.getCityId(), dataItem.getCityName(), dataItem.getStateName()));
                            Methods.progressDialogDismiss();
                        }
                        adapter.updateData(cities);
                    }
                } else {
                    Log.e(TAG, "Failed to retrieve cities: " + response.message());
                    Methods.progressDialogDismiss();
                }
            }

            @Override
            public void onFailure(Call<CityModel> call, Throwable t) {
                Log.e(TAG, "Error fetching cities: " + t.getMessage());
                Methods.progressDialogDismiss();
            }
        });
    }
}