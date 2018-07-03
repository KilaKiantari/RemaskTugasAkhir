package com.example.asus_desktop.remask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Asus-Desktop on 5/9/2018.
 */

public class ModelGroupAll {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("results")
    @Expose
    private  ArrayList<Result> results = null;
    @SerializedName("namagroup")
    @Expose
    private String namagroup;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNamaGroup() {
        return namagroup;
    }

    public void setNamagroup(String namagroup) {
        this.namagroup = namagroup;
    }


    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

}

