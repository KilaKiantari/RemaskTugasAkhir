package com.example.asus_desktop.remask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus-Desktop on 7/12/2018.
 */

public class ModelGrafikKeteranganLain {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("jmllain")
    @Expose
    private Integer jmllain;
    @SerializedName("jmlblmlain")
    @Expose
    private Integer jmlblmlain;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getJmllain() {
        return jmllain;
    }

    public void setJmllain(Integer jmllain) {
        this.jmllain = jmllain;
    }

    public Integer getJmlblmlain() {
        return jmlblmlain;
    }

    public void setJmlblmlain(Integer jmlblmlain) {
        this.jmlblmlain = jmlblmlain;
    }
}
