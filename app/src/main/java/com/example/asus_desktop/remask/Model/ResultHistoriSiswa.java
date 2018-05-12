package com.example.asus_desktop.remask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus-Desktop on 5/7/2018.
 */

public class ResultHistoriSiswa {
    @SerializedName("id_tugas")
    @Expose
    private String idTugas;
    @SerializedName("nama_tugas")
    @Expose
    private String namaTugas;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("status_tugas")
    @Expose
    private String statusTugas;
    @SerializedName("tanggal_tugas")
    @Expose
    private String tanggalTugas;
    @SerializedName("tanggal_selesai")
    @Expose
    private String tanggalSelesai;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("group_id")
    @Expose
    private String groupId;

    public String getIdTugas() {
        return idTugas;
    }

    public void setIdTugas(String idTugas) {
        this.idTugas = idTugas;
    }

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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatusTugas() {
        return statusTugas;
    }

    public void setStatusTugas(String statusTugas) {
        this.statusTugas = statusTugas;
    }

    public String getTanggalTugas() {
        return tanggalTugas;
    }

    public void setTanggalTugas(String tanggalTugas) {
        this.tanggalTugas = tanggalTugas;
    }

    public String getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(String tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

}

