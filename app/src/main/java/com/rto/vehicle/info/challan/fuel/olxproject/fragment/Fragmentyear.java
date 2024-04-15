package com.rto.vehicle.info.challan.fuel.olxproject.fragment;

import static android.content.ContentValues.TAG;
import static com.rto.vehicle.info.challan.fuel.olxproject.comman.GlobalAcesss.apiInterface;

import static java.lang.Integer.parseInt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.rto.vehicle.info.challan.fuel.olxproject.R;
import com.rto.vehicle.info.challan.fuel.olxproject.adpter.CityPincodeAdapter;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.Methods;
import com.rto.vehicle.info.challan.fuel.olxproject.comman.SharePrefs;
import com.rto.vehicle.info.challan.fuel.olxproject.model.MonthsModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.PincodeModel;
import com.rto.vehicle.info.challan.fuel.olxproject.model.YearModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmentyear#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmentyear extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Spinner spnr1styera, spnr2stmonth;

    public Fragmentyear() {
        // Required empty public constructor
    }


    public static Fragmentyear newInstance(String param1, String param2) {
        Fragmentyear fragment = new Fragmentyear();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_fragmentyear, container, false);

        spnr1styera = (Spinner) rootView.findViewById(R.id.spnr1styera);
        spnr2stmonth = (Spinner) rootView.findViewById(R.id.spnr2stmonth);

        searchRegistrationyear();
        searchRegistrationMonths();


        // Inflate the layout for this fragment
        return rootView;
    }


    private void searchRegistrationyear() {
        Methods.progressDialogShow(getActivity());
        Call<YearModel> call = apiInterface.registrationyear();
        call.enqueue(new Callback<YearModel>() {
            @Override
            public void onResponse(Call<YearModel> call, Response<YearModel> response) {
                if (response.isSuccessful()) {
                    YearModel cityModel = response.body();

                    Log.e("======@@City", "" + cityModel);

                    if (cityModel != null && cityModel.getData() != null) {


                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.item_year, R.id.tv_year,
                                cityModel.getData()) {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);

                                TextView text = view.findViewById(R.id.tv_year);

                                return view;
                            }
                        };
                        spnr1styera.setAdapter((SpinnerAdapter) arrayAdapter);
                        Methods.progressDialogDismiss();

                        spnr1styera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                // Display a Toast with the selected item
                                String selectedItem = parentView.getItemAtPosition(position).toString();
                                SharePrefs.editor("Regyear",parseInt(selectedItem));


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
            public void onFailure(Call<YearModel> call, Throwable t) {
                Log.e(TAG, "Error fetching cities: " + t.getMessage());
            }
        });
    }


    private void searchRegistrationMonths() {
        Methods.progressDialogShow(getActivity());
        Call<MonthsModel> call = apiInterface.registrationmonths();
        call.enqueue(new Callback<MonthsModel>() {
            @Override
            public void onResponse(Call<MonthsModel> call, Response<MonthsModel> response) {
                if (response.isSuccessful()) {
                    MonthsModel monthsModel = response.body();

                    Log.e("======@@City", "" + monthsModel);

                    if (monthsModel != null && monthsModel.getData() != null) {


                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.item_year, R.id.tv_year,
                                monthsModel.getData()) {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);

                                TextView text = view.findViewById(R.id.tv_year);

                                return view;
                            }
                        };
                        spnr2stmonth.setAdapter((SpinnerAdapter) arrayAdapter);
                        Methods.progressDialogDismiss();

                        spnr2stmonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                // Display a Toast with the selected item
                                String selectedItem = parentView.getItemAtPosition(position).toString();
                                SharePrefs.editor("RegMonths",selectedItem);

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
            public void onFailure(Call<MonthsModel> call, Throwable t) {
                Log.e(TAG, "Error fetching cities: " + t.getMessage());
            }
        });
    }
}