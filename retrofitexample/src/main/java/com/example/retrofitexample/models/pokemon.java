package com.example.retrofitexample.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luillo on 22/09/16.
 */
public class pokemon {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("height")
    private int height;

    @SerializedName("is_default")
    private boolean isDefault;

    @SerializedName("abilities")
    List<AbilityIndex>abilities;

    @SerializedName("species")
    private Species species;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public List<AbilityIndex> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilityIndex> abilities) {
        this.abilities = abilities;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
}
