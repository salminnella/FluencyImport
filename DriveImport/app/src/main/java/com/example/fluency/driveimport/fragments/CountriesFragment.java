package com.example.fluency.driveimport.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fluency.driveimport.R;
import com.example.fluency.driveimport.adapters.CountriesRvAdapter;
import com.example.fluency.driveimport.models.AllCountries;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by anthony on 10/24/16.
 */

public class CountriesFragment extends Fragment {
    private static final String TAG_COUNTRIES_FRAGMENT = "CountriesFragment";

    private RecyclerView countriesRecycerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_countries, container, false);
        initViews(view);

        return view;
    }

    private void initViews(View view) {
        countriesRecycerView = (RecyclerView) view.findViewById(R.id.countries_fragment_rv);
    }

    public void populateCountriesRecyclerView(JSONObject objectTable) {
        ArrayList<AllCountries> countriesArrayList = new ArrayList<>();

        try {
            JSONArray rows = objectTable.getJSONArray("rows");

            for (int r = 0; r < rows.length(); r++) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray column = row.getJSONArray("c");

                String countryName = column.getJSONObject(0).getString("v");
//                int countryCode = Integer.parseInt(column.getJSONObject(2).getString("v"));
                String countryCode = column.getJSONObject(2).getString("v");
                double codeDouble = Double.parseDouble(countryCode);
                int codeInt = (int) codeDouble;
                String countryCodeStr = "+" + codeInt;

                Log.d(TAG_COUNTRIES_FRAGMENT, "NAME = " + countryName);
                Log.d(TAG_COUNTRIES_FRAGMENT, "CODE = " + countryCode);
                Log.d(TAG_COUNTRIES_FRAGMENT, "CODE = " + codeDouble);
                Log.d(TAG_COUNTRIES_FRAGMENT, "CODE = " + codeInt);
                Log.d(TAG_COUNTRIES_FRAGMENT, "CODE = " + countryCodeStr);

                countriesArrayList.add(new AllCountries(countryName, countryCodeStr));
            }

            CountriesRvAdapter countriesRvAdapter = new CountriesRvAdapter(countriesArrayList);
            countriesRecycerView.setAdapter(countriesRvAdapter);
            LinearLayoutManager lManager = new LinearLayoutManager(getActivity());
            countriesRecycerView.setLayoutManager(lManager);
            countriesRecycerView.hasFixedSize();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
