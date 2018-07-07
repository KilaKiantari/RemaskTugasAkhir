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

    @SerializedName("id_group")
    @Expose
    private String idGroup;

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
    private String guruId;
    @SerializedName("orangtua_id")
    @Expose
    private Object orangtuaId;
    @SerializedName("siswa_id")
    @Expose
    private String siswaId;
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
    @SerializedName("id_progress")
    @Expose
    private String idProgress;
    @SerializedName("nama_progress")
    @Expose
    private String namaProgress;
    @SerializedName("tgl_selesai")
    @Expose
    private String tglSelesai;
    @SerializedName("status_progress")
    @Expose
    private String statusProgress;
    @SerializedName("tugas_id")
    @Expose
    private Integer tugasId;
    private boolean isSelected;

    @SerializedName("COUNT(*)")
    @Expose
    private String cOUNT;
    @SerializedName("selisihorganisasi")
    @Expose
    private Float selisihorganisasi;
    @SerializedName("selisihpendidikan")
    @Expose
    private Float selisihpendidikan;
    @SerializedName("selisihlain")
    @Expose
    private Float selisihlain;

    public Float getSelisihlain() {
        return selisihlain;
    }

    public void setSelisihlain(Float selisihlain) {
        this.selisihlain = selisihlain;
    }

    public Float getSelisihpendidikan() {
        return selisihpendidikan;
    }

    public void setSelisihpendidikan(Float selisihpendidikan) {
        this.selisihpendidikan = selisihpendidikan;
    }

    public Float getSelisihorganisasi() {
        return selisihorganisasi;
    }

    public void setSelisihorganisasi(Float selisihorganisasi) {
        this.selisihorganisasi = selisihorganisasi;
    }

    public String getCOUNT  () {
        return cOUNT;
    }

    public void setCOUNT(String cOUNT) {
        this.cOUNT = cOUNT;
    }


    public String getIdProgress() {
        return idProgress;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public void setIdProgress(String idProgress) {
        this.idProgress = idProgress;
    }

    public String getNamaProgress() {
        return namaProgress;
    }

    public void setNamaProgress(String namaProgress) {
        this.namaProgress = namaProgress;
    }

    public String getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(String tglSelesai) {
        this.tglSelesai = tglSelesai;
    }

    public String getStatusProgress() {
        return statusProgress;
    }

    public void setStatusProgress(String statusProgress) {
        this.statusProgress = statusProgress;
    }

    public Integer getTugasId() {
        return tugasId;
    }

    public void setTugasId(Integer tugasId) {
        this.tugasId = tugasId;
    }


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

    public String getGuruId() {
        return guruId;
    }

    public void setGuruId(String guruId) {
        this.guruId = guruId;
    }

    public Object getOrangtuaId() {
        return orangtuaId;
    }

    public void setOrangtuaId(Object orangtuaId) {
        this.orangtuaId = orangtuaId;
    }

    public String getSiswaId() {
        return siswaId;
    }

    public void setSiswaId(String siswaId) {
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}