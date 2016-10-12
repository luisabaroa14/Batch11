package com.example.googlemaps.modules;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luillo on 05/10/16.
 */
public class Properties {

    @SerializedName("sector")
    public String sector;

    @SerializedName("municipio")
    public String municipio;



    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
