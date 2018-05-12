package com.example.asus_desktop.remask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus-Desktop on 5/9/2018.
 */

public class ResultGroupAll {
    @SerializedName("namagroup")
    @Expose
    private String namagroup;
    @SerializedName("nama_guru")
    @Expose
    private String namaGuru;
    @SerializedName("nama_matpel")
    @Expose
    private String namaMatpel;
    @SerializedName("sekolah")
    @Expose
    private String sekolah;

    public String getNamagroup() {
        return namagroup;
    }

    public void setNamagroup(String namagroup) {
        this.namagroup = namagroup;
    }

    public String getNamaGuru() {
        return namaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        this.namaGuru = namaGuru;
    }

    public String getNamaMatpel() {
        return namaMatpel;
    }

    public void setNamaMatpel(String namaMatpel) {
        this.namaMatpel = namaMatpel;
    }

    public String getSekolah() {
        return sekolah;
    }

    public void setSekolah(String sekolah) {
        this.sekolah = sekolah;
    }

}
