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
import com.example.fluency.driveimport.adapters.LanguageRVAdapter;
import com.example.fluency.driveimport.models.Language;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by anthony on 10/24/16.
 */

public class LanguagesFragment extends Fragment {
    private static final String TAG_LANGUAGES_FRAGMENT = "LanguagesFragment";
    private RecyclerView langRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG_LANGUAGES_FRAGMENT, "onCreate: was called");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG_LANGUAGES_FRAGMENT, "onCreateView: was called");
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_languages, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        langRecyclerView = (RecyclerView) view.findViewById(R.id.lang_fragment_rv);
    }

    public void populateLanguageRecyclerView(JSONObject objectTable) {
        ArrayList<Language> languageArrayList = new ArrayList<>();
        try {
            JSONArray rows = objectTable.getJSONArray("rows");

            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                String language = columns.getJSONObject(0).getString("v");
                String ISO = columns.getJSONObject(1).getString("v");
                String phone = columns.getJSONObject(3).getString("v");
                int routing = columns.getJSONObject(4).getInt("v");
                int lCode = columns.getJSONObject(5).getInt("v");

                //get the countries colum
                String[] countriesArray;
                String countriesStr = columns.getJSONObject(8).getString("v");
                Log.i(TAG_LANGUAGES_FRAGMENT, "processJson: countriesStr = " + countriesStr);
                countriesArray = countriesStr.split("-");
//                if (countriesStr != null) {
                // trim the countries: label in the cell
                //Log.i(TAG, "processJson: countriesStr = " + countriesStr);
                //String countriesTrimmed = countriesStr.substring(11, countriesStr.length());
                //Log.i(TAG, "processJson: trimmed = " + countriesTrimmed);
                // split the countries by - into string array
                //countriesArray = countriesTrimmed.split("-");
//                }


                languageArrayList.add(new Language(language,phone,ISO,lCode,routing,countriesArray));
//                Log.i(TAG, "processJson: language " + language);
//                Log.i(TAG, "processJson: iso " + ISO);
//                Log.i(TAG, "processJson: vendor " + vendor);
//                Log.i(TAG, "processJson: phone " + phone);
//                Log.i(TAG, "processJson: routing " + routing);
//                Log.i(TAG, "processJson: lCode " + lCode);

            }

            LanguageRVAdapter languageRVAdapter = new LanguageRVAdapter(languageArrayList);
            langRecyclerView.setAdapter(languageRVAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            langRecyclerView.setLayoutManager(layoutManager);
            langRecyclerView.hasFixedSize();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
