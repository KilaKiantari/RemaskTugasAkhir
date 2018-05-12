package com.example.asus_desktop.remask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus-Desktop on 5/9/2018.
 */

public class ResultSkalaPrioritas {

    @SerializedName("nama_tugas")
    @Expose
    private String namaTugas;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    @SerializedName("tanggal_tugas")
    @Expose
    private String tanggalTugas;

    public String getNamaTugas() {
        return namaTugas;
    }

    public void setNamaTugas(String namaTugas) {
        this.namaTugas = namaTugas;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getTanggalTugas() {
        return tanggalTugas;
    }

    public void setTanggalTugas(String tanggalTugas) {
        this.tanggalTugas = tanggalTugas;
    }

}

