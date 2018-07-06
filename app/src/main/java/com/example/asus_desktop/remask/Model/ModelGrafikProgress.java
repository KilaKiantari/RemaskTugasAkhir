package com.example.asus_desktop.remask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus-Desktop on 7/4/2018.
 */

public class ModelGrafikProgress {


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("jml")
    @Expose
    private Integer jml;

    @SerializedName("jmlblm")
    @Expose
    private Integer jmlblm;
    public Integer getJmlblm() {
        return jmlblm;
    }

    public void setJmlblm(Integer jmlblm) {
        this.jmlblm = jmlblm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getJml() {
        return jml;
    }

    public void setJml(Integer jml) {
        this.jml = jml;
    }
}
