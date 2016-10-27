package com.example.fluency.driveimport.models;

/**
 * Created by anthony on 10/25/16.
 */

public class AllCountries {
    private String countryPhoneName;
    private String countryPhoneCode;

    public AllCountries() {

    }

    public AllCountries(String countryPhoneName, String countryPhoneCode) {
        this.countryPhoneName = countryPhoneName;
        this.countryPhoneCode = countryPhoneCode;
    }

    public String getCountryPhoneName() {
        return countryPhoneName;
    }

    public void setCountryPhoneName(String countryPhoneName) {
        this.countryPhoneName = countryPhoneName;
    }

    public String getCountryPhoneCode() {
        return countryPhoneCode;
    }

    public void setCountryPhoneCode(String countryPhoneCode) {
        this.countryPhoneCode = countryPhoneCode;
    }
}
