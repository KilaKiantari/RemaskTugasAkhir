package com.example.asus_desktop.remask;

/**
 * Created by Asus-Desktop on 4/12/2018.
 */

public class Mahasiswa {

    private String nama;
    private String npm;
    private String nohp;

    public Mahasiswa(String nama,String npm) {
        this.nama = nama;
        this.npm = npm;
        this.nohp = nohp;

    }

    public String getNama() {

        return nama;
    }

    public void setNama(String nama) {

        this.nama = nama;
    }

    public String getNpm() {

        return npm;
    }

    public void setNpm(String npm) {

        this.npm = npm;
    }
}