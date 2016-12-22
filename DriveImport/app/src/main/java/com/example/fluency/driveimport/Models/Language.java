package com.example.fluency.driveimport.models;

import java.util.ArrayList;

/**
 * Created by anthony on 9/2/16.
 */
public class Language {
    private String name;
    private String phone;
    private String ISO;
    private int numSpeakers;
    private int lCode;
    private int routing;
    private ArrayList<LanguageCountry> languageCountry;

    public Language() {
    }

    public Language(String name, String phone, String ISO, int lCode, int routing, int numSpeakers, String[] countryArray) {
        this.name = name;
        this.phone = phone;
        this.ISO = ISO;
        this.lCode = lCode;
        this.routing = routing;
        this.numSpeakers = numSpeakers;

        setLanguageCountry(countryArray);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getISO() {
        return ISO;
    }

    public void setISO(String ISO) {
        this.ISO = ISO;
    }

    public int getlCode() {
        return lCode;
    }

    public void setlCode(int lCode) {
        this.lCode = lCode;
    }

    public int getRouting() {
        return routing;
    }

    public void setRouting(int routing) {
        this.routing = routing;
    }

    public int getNumSpeakers() {
        return numSpeakers;
    }

    public void setNumSpeakers(int numSpeakers) {
        this.numSpeakers = numSpeakers;
    }

    public ArrayList<com.example.fluency.driveimport.models.LanguageCountry> getLanguageCountry() {
        return languageCountry;
    }

    public void setLanguageCountry(String[] countryArray) {
        languageCountry = new ArrayList<>();
        for (String countryName : countryArray) {
            languageCountry.add(new com.example.fluency.driveimport.models.LanguageCountry(countryName));
        }
    }
}
