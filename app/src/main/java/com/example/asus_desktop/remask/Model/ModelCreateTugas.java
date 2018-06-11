package com.example.asus_desktop.remask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus-Desktop on 5/12/2018.
 */

public class ModelCreateTugas {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("id_tugas")
    @Expose
    private Integer idTugas;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdTugas() {
        return idTugas;
    }

    public void setIdTugas(Integer idTugas) {
        this.idTugas = idTugas;
    }


}
