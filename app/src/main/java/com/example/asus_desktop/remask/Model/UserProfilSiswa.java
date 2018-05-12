package com.example.asus_desktop.remask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus-Desktop on 5/7/2018.
 */

public class UserProfilSiswa {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("results")
    @Expose
    private Result results;

    public String getStatus() {
        return status;
    }

    public Result getResults() {
        return results;
    }
}
