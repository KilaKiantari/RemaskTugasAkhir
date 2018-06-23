package com.example.asus_desktop.remask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus-Desktop on 5/29/2018.
 */

public class ModelRegister {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("id_siswa")
    @Expose
    private Integer idSiswa;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdSiswa() {
        return idSiswa;
    }

    public void setIdSiswa(Integer idSiswa) {
        this.idSiswa = idSiswa;
    }

}
