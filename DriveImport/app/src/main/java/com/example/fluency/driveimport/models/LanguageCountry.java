package com.example.fluency.driveimport.models;

/**
 * Created by anthony on 9/2/16.
 */
public class LanguageCountry {
    private String countryName;

    public LanguageCountry() {
    }

    public LanguageCountry(String countryName) {
        this.countryName = countryName;
    }


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
