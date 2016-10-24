package com.example.fluency.driveimport;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fluency.driveimport.models.Country;
import com.example.fluency.driveimport.models.Language;

import java.util.List;

/**
 * Created by anthony on 9/4/16.
 */
public class LanguageRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Language> languageList;

    public LanguageRVAdapter(List<Language> data) {
        languageList = data;
    }

    public class LanguageListViewHolder extends RecyclerView.ViewHolder {
        private TextView langName;
        private TextView langIso;
//        private TextView langPhone;
        private TextView langLcode;
        private TextView langCountries;

        public LanguageListViewHolder(View itemView) {
            super(itemView);

            langName = (TextView) itemView.findViewById(R.id.name_rv_layout);
            langIso = (TextView) itemView.findViewById(R.id.iso_rv_layout);
//            langPhone = (TextView) itemView.findViewById(R.id.phone_rv_layout);
            langLcode = (TextView) itemView.findViewById(R.id.l_code_rv_layout);
            langCountries = (TextView) itemView.findViewById(R.id.countries_rv_layout);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.languages_rv_layout, parent, false);
        return new LanguageListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Language language = languageList.get(position);

        LanguageListViewHolder langViewHolder = (LanguageListViewHolder) holder;

        langViewHolder.langName.setText(language.getName());
        langViewHolder.langIso.setText(language.getISO());
//        langViewHolder.langPhone.setText(language.getPhone());
        langViewHolder.langLcode.setText(String.valueOf(language.getlCode()));
        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < languageList.get(i).getCountry().size(); i++)
//        {
//            sb.append(languageList.get(i).getCountry());
//            sb.append("\t");
//        }
        for (Country country : languageList.get(position).getCountry()) {
            sb.append(country.getCountryName());
            sb.append("\n");
        }
        langViewHolder.langCountries.setText(sb);

    }

    @Override
    public int getItemCount() {
        return languageList.size();
    }
}
