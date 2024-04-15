package com.rto.vehicle.info.challan.fuel.olxproject.fragment;

import static android.content.ContentValues.TAG;
import static com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss.apiInterface;
import static java.lang.Integer.parseInt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.adpter.SpinnerVariantAdapter;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.Methods;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.SharePrefs;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.BrandModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.CarModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.VariantModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.YearModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragmentvariant extends Fragment {
    Spinner spnr3version, spnr3fuletype,spnr3colour;
    private List<VariantModel.DataItem> VariantItems  ;
    SpinnerVariantAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragmentvariant, container, false);

        spnr3version = (Spinner) rootView.findViewById(R.id.spnr3version);
        spnr3fuletype = (Spinner) rootView.findViewById(R.id.spnr3fuletype);
        spnr3colour = (Spinner) rootView.findViewById(R.id.spnr3colour);

        return rootView;
    }


    private void searchversion() {
        Methods.progressDialogShow(getActivity());
        Call<VariantModel> call = apiInterface.searchVariantModel(SharePrefs.getcarModel_ID(),SharePrefs.getRegistrationyear());
        call.enqueue(new Callback<VariantModel>() {
            @Override
            public void onResponse(Call<VariantModel> call, Response<VariantModel> response) {
                if (response.isSuccessful()) {
                    VariantModel variantModel = response.body();

                    Log.e("======@@City", "" + variantModel);

                    if (variantModel != null && variantModel.getData() != null) {


                        List<VariantModel.DataItem> dataItems = variantModel.getData();
                        Log.e("======@@City1", "" + dataItems.size());
                        VariantItems = new ArrayList<>();
                        Log.e("======@@City2", "" + VariantItems.size());
                        for (VariantModel.DataItem dataItem : dataItems) {
                            Log.e("======@@City3", "" + dataItems);
                            VariantItems.add(new VariantModel.DataItem(dataItem.getID(), dataItem.getName(),dataItem.getFuelType(),dataItem.getTransmission()));
                            Methods.progressDialogDismiss();
                        }




                         adapter = new SpinnerVariantAdapter(getActivity(), R.layout.item_veriant, VariantItems);
                        spnr3version.setAdapter(adapter);




                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.item_year, R.id.tv_year,
                                variantModel.getData()) {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);

                                TextView text = view.findViewById(R.id.tv_year);

                                return view;
                            }
                        };
                        spnr3version.setAdapter((SpinnerAdapter) arrayAdapter);
                        Methods.progressDialogDismiss();

                        spnr3version.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                // Display a Toast with the selected item
                                String selectedItem = parentView.getItemAtPosition(position).toString();
                                SharePrefs.editor("Regyear", parseInt(selectedItem));


                                Toast.makeText(getActivity(), "Selected item: " + parseInt(selectedItem), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                                // Do nothing if nothing is selected
                            }
                        });


                    }
                } else {
                    Log.e(TAG, "Failed to retrieve cities: " + response.message());
                    Methods.progressDialogDismiss();

                }
            }

            @Override
            public void onFailure(Call<VariantModel> call, Throwable t) {
                Log.e(TAG, "Error fetching cities: " + t.getMessage());
            }
        });
    }
}