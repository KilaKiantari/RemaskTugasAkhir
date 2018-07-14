package com.example.asus_desktop.remask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus-Desktop on 7/12/2018.
 */

public class ModelGrafikKeterangan {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("jmlpendidikan")
    @Expose
    private Integer jmlpendidikan;
    @SerializedName("jmlblmpendidikan")
    @Expose
    private Integer jmlblmpendidikan;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getJmlpendidikan() {
        return jmlpendidikan;
    }

    public void setJmlpendidikan(Integer jmlpendidikan) {
        this.jmlpendidikan = jmlpendidikan;
    }

    public Integer getJmlblmpendidikan() {
        return jmlblmpendidikan;
    }

    public void setJmlblmpendidikan(Integer jmlblmpendidikan) {
        this.jmlblmpendidikan = jmlblmpendidikan;
    }

}
