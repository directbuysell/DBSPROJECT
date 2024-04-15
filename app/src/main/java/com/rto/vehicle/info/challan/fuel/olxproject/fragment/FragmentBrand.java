package com.rto.vehicle.info.challan.fuel.olxproject.fragment;

import static com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss.apiInterface;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.adpter.BrandAdapter;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.Methods;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.BrandModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentBrand extends Fragment {

   RecyclerView rv_brand;
    private BrandAdapter adapter;
    private List<BrandModel.DataItem> groupList = new ArrayList<>();
    private List<BrandModel.MakesItem> allMakesList = new ArrayList<>();
    private EditText etSearchKeyword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_brand, container, false);
        // Inflate the layout for this fragment

        rv_brand = (RecyclerView) rootView.findViewById(R.id.rv_brand);


        etSearchKeyword = (EditText) rootView.findViewById(R.id.etSearchKeyword);



        // Set up RecyclerView
        rv_brand.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
        adapter = new BrandAdapter(getActivity(), groupList, allMakesList);
        rv_brand.setAdapter(adapter);


        SearchCarBrand(getActivity());


        etSearchKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filterMakes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        return rootView;
    }

    private void SearchCarBrand(FragmentActivity activity) {



        Methods.progressDialogShow(activity);
        Call<BrandModel> call = apiInterface.carBrand();
        call.enqueue(new Callback<BrandModel>() {
            @Override
            public void onResponse(Call<BrandModel> call, Response<BrandModel> response) {
                if (response.isSuccessful()) {
                    BrandModel brandModel = response.body();
                    if (brandModel != null) {
                        groupList.addAll(brandModel.getData());
                        for (BrandModel.DataItem dataItem : brandModel.getData()) {
                            allMakesList.addAll(dataItem.getMakes());
                        }

                        rv_brand.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, true));
                        adapter = new BrandAdapter(getActivity(), groupList, allMakesList);
                        rv_brand.setAdapter(adapter);


                        adapter.notifyDataSetChanged();
                        Methods.progressDialogDismiss();
                    }
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(getActivity(), "Failed to fetch data", Toast.LENGTH_SHORT).show();
                    Methods.progressDialogDismiss();
                }
            }

            @Override
            public void onFailure(Call<BrandModel> call, Throwable t) {
                // Handle network errors
                Toast.makeText(getActivity(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}