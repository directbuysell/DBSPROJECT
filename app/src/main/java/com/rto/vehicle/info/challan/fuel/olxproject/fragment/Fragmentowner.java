package com.rto.vehicle.info.challan.fuel.olxproject.fragment;

import static android.content.ContentValues.TAG;
import static com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss.apiInterface;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.adpter.OwnerAdapter;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.Methods;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.SharePrefs;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.BrandModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.VariantModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.OwnersModel;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragmentowner extends Fragment {


    ListView iv_owner;
    public int selectedPos = 0;
    private List<OwnersModel> ownersModels = new ArrayList<>();
    OwnerAdapter ownerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragmentowner, container, false);
        iv_owner = (ListView) rootView.findViewById(R.id.lv_owner);
        searchowner(getActivity());


        return rootView;
    }


    private void searchowner(FragmentActivity activity) {
        Methods.progressDialogShow(getActivity());
        Call<OwnersModel> call = apiInterface.registrationOwners();
        call.enqueue(new Callback<OwnersModel>() {
            @Override
            public void onResponse(Call<OwnersModel> call, Response<OwnersModel> response) {
                if (response.isSuccessful()) {
                    OwnersModel ownersModel = response.body();

                    Log.e("======@@City", "" + ownersModel);

                    if (ownersModel != null && ownersModel.getData() != null) {


                        ownerAdapter = new OwnerAdapter(getActivity(), ownersModel.getData());

                        iv_owner.setAdapter(ownerAdapter);

                        iv_owner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ownerAdapter.setSelectedItem(position);
//                                Toast.makeText(getActivity(), "Selected Item: " + ownersModels.get(position), Toast.LENGTH_SHORT).show();
                            }


                        });

                    }
                } else {
                    Log.e(TAG, "Failed to retrieve cities: " + response.message());
                    Methods.progressDialogDismiss();

                }
            }

            @Override
            public void onFailure(Call<OwnersModel> call, Throwable t) {
                Log.e(TAG, "Error fetching cities: " + t.getMessage());
            }
        });
    }
}