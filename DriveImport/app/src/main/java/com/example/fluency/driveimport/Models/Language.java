package com.example.fluency.driveimport.models;

import java.util.ArrayList;

/**
 * Created by anthony on 9/2/16.
 */
public class Language {
    private String name;
    private String phone;
    private String ISO;
    private int lCode;
    private int routing;
    private ArrayList<Country> country;

    public Language() {
    }

    public Language(String name, String phone, String ISO, int lCode, int routing, String[] countryArray) {
        this.name = name;
        this.phone = phone;
        this.ISO = ISO;
        this.lCode = lCode;
        this.routing = routing;

        setCountry(countryArray);
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

    public ArrayList<Country> getCountry() {
        return country;
    }

    public void setCountry(String[] countryArray) {
        country = new ArrayList<>();
        for (String countryName : countryArray) {
            country.add(new Country(countryName));
        }
    }
}
