package com.example.asus_desktop.remask.Model;

/**
 * Created by Asus-Desktop on 5/6/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

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

    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;

    @SerializedName("nama_orangtua")
    @Expose
    private String namaOrangtua;

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("auth_key")
    @Expose
    private String authKey;
    @SerializedName("password_hash")
    @Expose
    private String passwordHash;
    @SerializedName("password_reset_token")
    @Expose
    private Object passwordResetToken;
    @SerializedName("guru_id")
    @Expose
    private Integer guruId;
    @SerializedName("orangtua_id")
    @Expose
    private Object orangtuaId;
    @SerializedName("siswa_id")
    @Expose
    private Object siswaId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private Integer createdAt;
    @SerializedName("updated_at")
    @Expose
    private Integer updatedAt;
    @SerializedName("level")
    @Expose
    private Integer level;

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

    public void setSekolah(String sekolah) {
        this.sekolah = sekolah;
    }


    public String getNamaLengkap() {
        return namaLengkap;
    }

    public String getSekolah() {
        return sekolah;
    }

    public String getNamaOrangtua() {
        return namaOrangtua;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Object getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(Object passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public Integer getGuruId() {
        return guruId;
    }

    public void setGuruId(Integer guruId) {
        this.guruId = guruId;
    }

    public Object getOrangtuaId() {
        return orangtuaId;
    }

    public void setOrangtuaId(Object orangtuaId) {
        this.orangtuaId = orangtuaId;
    }

    public Object getSiswaId() {
        return siswaId;
    }

    public void setSiswaId(Object siswaId) {
        this.siswaId = siswaId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

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