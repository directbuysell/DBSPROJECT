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

import com.rto.vehicle.info.challan.fuel.olxproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPlanning#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPlanning extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView iv_planning;

    public FragmentPlanning() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPlanning.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPlanning newInstance(String param1, String param2) {
        FragmentPlanning fragment = new FragmentPlanning();
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
        View rootView = inflater.inflate(R.layout.fragment_planning, container, false);

        iv_planning = (ListView) rootView.findViewById(R.id.lv_planning);
        Setplanning(getActivity());


        return rootView;
    }

    private void Setplanning(FragmentActivity activity) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("Immediately");
        arrayList.add("Within a month");
        arrayList.add("After a month");
        arrayList.add("Just checking price");



        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, arrayList);
        iv_planning.setAdapter(adapter);

        iv_planning.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
//                selectedText.setText("Selected Item: " + selectedItem);
            }
        });
    }
}