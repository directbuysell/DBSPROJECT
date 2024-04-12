package com.rto.vehicle.info.challan.fuel.olxproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SpinnerAdapter;

import com.rto.vehicle.info.challan.fuel.olxproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmentodometer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmentodometer extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    ListView iv_km;


    public Fragmentodometer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragmentodometer.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmentodometer newInstance(String param1, String param2) {
        Fragmentodometer fragment = new Fragmentodometer();
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
        View rootView = inflater.inflate(R.layout.fragment_fragmentodometer, container, false);

        iv_km = (ListView) rootView.findViewById(R.id.iv_km);

        setkm(getActivity());
        // Inflate the layout for this fragment
        return rootView;
    }

    public void setkm(FragmentActivity activity) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("0 Km - 10,000 Km");
        arrayList.add("10,000 Km - 20,000 Km");
        arrayList.add("20,000 Km - 30,000 Km");
        arrayList.add("30,000 Km - 40,000 Km");
        arrayList.add("50,000 km - 60,000Km");

        arrayList.add("60,000 Km - 70,000 Km");
        arrayList.add("70,000 Km - 80,000 Km");
        arrayList.add("90,000 Km - 1,00,000 Km");
        arrayList.add("1,00,000 km - 1,25,000 Km");
        arrayList.add("1,25,000 km - 1,50,000 Km");
        arrayList.add("1,50,000 Km - 1,75,000 Km");
        arrayList.add("1,75,000 Km - 2,00,000 Km");
        arrayList.add("2,00,000 Km - 2,25,000 Km");
        arrayList.add("2,25,000 km - 2,50,000 Km");
        arrayList.add("2,50,000 km or more");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, arrayList);
        iv_km.setAdapter(adapter);

        iv_km.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
//                selectedText.setText("Selected Item: " + selectedItem);
            }
        });


//        ArrayAdapter arrayAdapter = new ArrayAdapter(this.activity, android.R.layout.simple_spinner_item, arrayList);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        this.spnr2ndNomineeRelation.setAdapter((SpinnerAdapter) arrayAdapter);
    }
}