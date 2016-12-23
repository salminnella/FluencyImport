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
import com.firebase.client.Firebase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by anthony on 10/24/16.
 */

public class LanguagesFragment extends Fragment {
    private static final String TAG_LANGUAGES_FRAGMENT = "LanguagesFragment";
    private static final String FIREBASE_ROOT_URL = "https://project-5176964787746948725.firebaseio.com/";
    private static final String FIREBASE_ROOT_CHILD_LANGUAGES = "SingleLanguageList";

    private RecyclerView langRecyclerView;
    private ArrayList<Language> languageArrayList;
    private Firebase firebaseRoot;
    private Firebase firebaseLanguagesRef;

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
        languageArrayList = new ArrayList<>();
        String language;
        String ISO;
        String phone;
        int routing;
        int lCode;
        int speakers;
        try {
            JSONArray rows = objectTable.getJSONArray("rows");

            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");


                language = columns.getJSONObject(0).getString("v");
                if (columns.get(1).toString().equals("null")) {
                    ISO = "0";
                } else {
                    ISO = columns.getJSONObject(1).getString("v");
                }
                if (columns.get(3).toString().equals("null")) {
                    phone = "0";
                } else {
                    phone = columns.getJSONObject(3).getString("v");
                }
                if (columns.get(4).toString().equals("null")) {
                    routing = 0;
                } else {
                    routing = columns.getJSONObject(4).getInt("v");
                }
                if (columns.get(5).toString().equals("null")) {
                    lCode = 0;
                } else {
                    lCode = columns.getJSONObject(5).getInt("v");
                }
                if (columns.get(8).toString().equals("null")) {
                    speakers = 0;
                } else {
                    speakers = columns.getJSONObject(8).getInt("v");
                }

                //get the countries column
                String[] countriesArray;
                String countriesStr = columns.getJSONObject(9).getString("v");
                Log.d(TAG_LANGUAGES_FRAGMENT, "processJson: countriesStr = " + countriesStr);
                countriesArray = countriesStr.split("-");


                languageArrayList.add(new Language(language,phone,ISO,lCode,routing, speakers, countriesArray));

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

    public void addLanguagesToFirebase() {
        Firebase.setAndroidContext(getContext());
        firebaseRoot = new Firebase(FIREBASE_ROOT_URL);
        firebaseLanguagesRef = firebaseRoot.child(FIREBASE_ROOT_CHILD_LANGUAGES);

        for (int i = 0; i < languageArrayList.size(); i++) {
            firebaseLanguagesRef.child(languageArrayList.get(i).getName()).setValue(languageArrayList.get(i));
            Log.d(TAG_LANGUAGES_FRAGMENT, "setValueToFirebase: name" + languageArrayList.get(i).getName());
        }
    }
}
