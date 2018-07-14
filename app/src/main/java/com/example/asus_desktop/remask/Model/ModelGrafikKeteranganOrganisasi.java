package com.example.asus_desktop.remask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus-Desktop on 7/12/2018.
 */

public class ModelGrafikKeteranganOrganisasi {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("jmlor")
    @Expose
    private Integer jmlor;
    @SerializedName("jmlblmor")
    @Expose
    private Integer jmlblmor;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getJmlor() {
        return jmlor;
    }

    public void setJmlor(Integer jmlor) {
        this.jmlor = jmlor;
    }

    public Integer getJmlblmor() {
        return jmlblmor;
    }

    public void setJmlblmor(Integer jmlblmor) {
        this.jmlblmor = jmlblmor;
    }
}
