package com.rto.vehicle.info.challan.fuel.olxproject.fragment;

import static android.content.ContentValues.TAG;
import static com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss.apiInterface;
import static java.lang.Integer.parseInt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

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
import com.rto.vehicle.info.challan.fuel.olxproject.adpter.SpinnerVersionAdapter;
import com.rto.vehicle.info.challan.fuel.olxproject.adpter.SpinnerColourAdapter;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.Methods;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.SharePrefs;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.AlternateModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.ColourModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.FuleModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.Other.VariantModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.YearModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragmentvariant extends Fragment {
    Spinner spnr3version, spnr3fuletype, spnr3fulealternatetype, spnr3colour;
    private List<VariantModel.DataItem> VariantItems;
    private List<ColourModel.DataItem> ColourItems;
    SpinnerVersionAdapter mAdapter;
    SpinnerColourAdapter spinnerColourAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragmentvariant, container, false);

        spnr3version = (Spinner) rootView.findViewById(R.id.spnr3version);
        spnr3fuletype = (Spinner) rootView.findViewById(R.id.spnr3fuletype);
        spnr3fulealternatetype = (Spinner) rootView.findViewById(R.id.spnr3fulealternatetype);
        spnr3colour = (Spinner) rootView.findViewById(R.id.spnr3colour);

        searchversion();
        searchFuleType();
        searchFulealternateType();


        return rootView;
    }


    private void searchversion() {
        Methods.progressDialogShow(getActivity());
        Log.e("=====version", "" + SharePrefs.getcarModel_ID() + "=====>" + SharePrefs.getRegistrationyear());
        Call<VariantModel> call = apiInterface.searchVariantModel(SharePrefs.getcarModel_ID(), SharePrefs.getRegistrationyear());
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
                            VariantItems.add(new VariantModel.DataItem(dataItem.getID(), dataItem.getName(), dataItem.getFuelType(), dataItem.getTransmission()));

                        }


                        mAdapter = new SpinnerVersionAdapter(getActivity(), VariantItems);
                        spnr3version.setAdapter(mAdapter);

                        spnr3version.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                VariantModel.DataItem clickedItem = (VariantModel.DataItem) parent.getItemAtPosition(position);
                                String clickedCountryName = clickedItem.getName();
                                SharePrefs.editor("Carvaeiant", clickedItem.getName());
                                SharePrefs.editor("FuleType", clickedItem.getFuelType());
                                SharePrefs.editor("Transmission", clickedItem.getTransmission());
                                SharePrefs.editor("vaeiantID", clickedItem.getID());
                                Methods.progressDialogDismiss();
//                                searchColour();
                                searchColour(clickedItem.getID());
                                Toast.makeText(getActivity(), clickedCountryName + " selected", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                        Methods.progressDialogDismiss();


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


    private void searchFuleType() {
        Methods.progressDialogShow(getActivity());
        Call<FuleModel> call = apiInterface.searchfuel();
        call.enqueue(new Callback<FuleModel>() {
            @Override
            public void onResponse(Call<FuleModel> call, Response<FuleModel> response) {
                if (response.isSuccessful()) {
                    FuleModel fuleModel = response.body();

                    Log.e("======@@City", "" + fuleModel);

                    if (fuleModel != null && fuleModel.getData() != null) {


                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.item_fule, R.id.tv_fule,
                                fuleModel.getData()) {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);


                                return view;
                            }
                        };
                        spnr3fuletype.setAdapter((SpinnerAdapter) arrayAdapter);


                        spnr3fuletype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                // Display a Toast with the selected item
                                String selectedItem = parentView.getItemAtPosition(position).toString();
                                Methods.progressDialogDismiss();


                                Toast.makeText(getActivity(), "Selected item: " + selectedItem, Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<FuleModel> call, Throwable t) {
                Log.e(TAG, "Error fetching cities: " + t.getMessage());
            }
        });
    }

    private void searchFulealternateType() {
        Methods.progressDialogShow(getActivity());
        Call<AlternateModel> call = apiInterface.searchalternate_fuel();
        call.enqueue(new Callback<AlternateModel>() {
            @Override
            public void onResponse(Call<AlternateModel> call, Response<AlternateModel> response) {
                if (response.isSuccessful()) {
                    AlternateModel fuleModel = response.body();

                    Log.e("======@@City", "" + fuleModel);

                    if (fuleModel != null && fuleModel.getData() != null) {


                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.item_alternatefule, R.id.tv_fule,
                                fuleModel.getData()) {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);


                                return view;
                            }
                        };
                        spnr3fulealternatetype.setAdapter((SpinnerAdapter) arrayAdapter);


                        spnr3fulealternatetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                // Display a Toast with the selected item
                                String selectedItem = parentView.getItemAtPosition(position).toString();
                                Methods.progressDialogDismiss();


                                Toast.makeText(getActivity(), "Selected item: " + selectedItem, Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<AlternateModel> call, Throwable t) {
                Log.e(TAG, "Error fetching cities: " + t.getMessage());
            }
        });
    }


    private void searchColour(int id) {
//        Methods.progressDialogShow(getActivity());
        Call<ColourModel> call = apiInterface.searchColourType(id);
        Log.e("======@@Cityidss", "" + id);
        call.enqueue(new Callback<ColourModel>() {
            @Override
            public void onResponse(Call<ColourModel> call, Response<ColourModel> response) {
                if (response.isSuccessful()) {
                    ColourModel colourModel = response.body();

                    Log.e("======@@City", "" + colourModel);

                    if (colourModel != null && colourModel.getData() != null) {


                        List<ColourModel.DataItem> dataItems = colourModel.getData();
                        Log.e("======@@City1", "" + dataItems.size());
                        ColourItems = new ArrayList<>();
                        Log.e("======@@City2", "" + ColourItems.size());
                        for (ColourModel.DataItem dataItem : dataItems) {
                            Log.e("======@@City3", "" + dataItems);
                            ColourItems.add(new ColourModel.DataItem(dataItem.getName(), dataItem.getValue(), dataItem.getDisplayName()));

                        }


                        spinnerColourAdapter = new SpinnerColourAdapter(getActivity(), ColourItems);
                        spnr3colour.setAdapter(spinnerColourAdapter);

                        spnr3colour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                ColourModel.DataItem clickedItem = (ColourModel.DataItem) parent.getItemAtPosition(position);
                                String clickedCountryName = clickedItem.getDisplayName();

                                Methods.progressDialogDismiss();

                                Toast.makeText(getActivity(), clickedCountryName + " selected", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                        Methods.progressDialogDismiss();


                    }
                } else {
                    Log.e(TAG, "Failed to retrieve cities: " + response.message());
                    Methods.progressDialogDismiss();

                }
            }

            @Override
            public void onFailure(Call<ColourModel> call, Throwable t) {
                Log.e(TAG, "Error fetching cities: " + t.getMessage());
            }
        });
    }
}