package com.example.fluency.driveimport.models;

/**
 * Created by anthony on 10/25/16.
 */

public class AllCountries {
    private String countryName;
    private String countryCode;

    public AllCountries() {

    }

    public AllCountries(String countryName, String countryCode) {
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
