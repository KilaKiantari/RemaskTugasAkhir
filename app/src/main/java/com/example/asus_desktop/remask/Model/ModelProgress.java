package com.example.asus_desktop.remask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus-Desktop on 6/6/2018.
 */

public class ModelProgress {
    @SerializedName("status")
    @Expose
    private  String status;


    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


}
