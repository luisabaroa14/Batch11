package com.example.retrofitexample.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luillo on 22/09/16.
 */
public class AbilityIndex {

    @SerializedName("is_hidden")
    private boolean isHidden;

    public boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }
}
