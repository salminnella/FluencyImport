package com.example.fluency.driveimport.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fluency.driveimport.R;

/**
 * Created by anthony on 10/24/16.
 */

public class LanguagesFragment extends Fragment{
    private static final String TAG_LANGUAGES_FRAGMENT = "LanguagesFragment";

    private Button langOpenFileButton;
    private Button langImportFbButton;
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
        langOpenFileButton = (Button) view.findViewById(R.id.lang_fragment_openfile_button);
        langImportFbButton = (Button) view.findViewById(R.id.lang_fragment_import_firebase_button);
        langRecyclerView = (RecyclerView) view.findViewById(R.id.lang_fragment_rv);
    }
}
