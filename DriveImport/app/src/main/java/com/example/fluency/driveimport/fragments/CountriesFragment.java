package com.example.fluency.driveimport.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fluency.driveimport.R;

/**
 * Created by anthony on 10/24/16.
 */

public class CountriesFragment extends Fragment {
    private static final String TAG_COUNTRIES_FRAGMENT = "CountriesFragment";

//    private Button countiresOpenFileButton;
//    private Button countriesImportFbButton;
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
//        countiresOpenFileButton = (Button) view.findViewById(R.id.countries_fragment_openfile_button);
//        countriesImportFbButton = (Button) view.findViewById(R.id.countries_fragment_import_firebase_button);
        countriesRecycerView = (RecyclerView) view.findViewById(R.id.countries_fragment_rv);
    }
}
