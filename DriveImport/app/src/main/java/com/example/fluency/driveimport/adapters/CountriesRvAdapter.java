package com.example.fluency.driveimport.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fluency.driveimport.R;
import com.example.fluency.driveimport.models.AllCountries;

import java.util.List;

/**
 * Created by anthony on 10/25/16.
 */

public class CountriesRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<AllCountries> allCountries;


    public CountriesRvAdapter(List<AllCountries> allCountries) {
        this.allCountries = allCountries;
    }

    public class CountriesViewHolder extends RecyclerView.ViewHolder{
        private TextView countryName;
        private TextView countryCode;

        public CountriesViewHolder(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.country_rv_name);
            countryCode = (TextView) itemView.findViewById(R.id.country_rv_code);
        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.countries_rv_layout, parent, false);

        return new CountriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AllCountries country = allCountries.get(position);

        CountriesViewHolder countriesViewHolder = (CountriesViewHolder) holder;

        countriesViewHolder.countryName.setText(country.getCountryName());
        countriesViewHolder.countryCode.setText(country.getCountryCode());
    }

    @Override
    public int getItemCount() {
        return allCountries.size();
    }
}
