package com.example.asus_desktop.remask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Asus-Desktop on 5/16/2018.
 */

public class ModelGroupJoined {


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("results")
    @Expose
    private ArrayList<Result> results = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

}
