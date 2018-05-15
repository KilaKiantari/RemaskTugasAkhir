package com.example.asus_desktop.remask.Api;

import com.example.asus_desktop.remask.Model.ModelCreateTugas;
import com.example.asus_desktop.remask.Model.ModelDaftarCatatan;
import com.example.asus_desktop.remask.Model.ModelGroupAll;
import com.example.asus_desktop.remask.Model.ModelLoginUser;
import com.example.asus_desktop.remask.Model.ModelSkalaPrioritas;
import com.example.asus_desktop.remask.Model.UserHistoriSiswa;
import com.example.asus_desktop.remask.Model.UserProfilSiswa;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Asus-Desktop on 5/6/2018.
 */

public class ApiClient {
    public static final String BASE_URL = "http://192.168.1.9/Remask/belakang/api/";

    public static PostServices services_post = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.PostServices.class);

    public static PostServicesCatatan services_post_catatan = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.PostServicesCatatan.class);

    public static GetServicesProfil services_get_profil = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesProfil.class);

    public static GetServicesHistori services_get_hitori = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesHistori.class);

    public static GetServicesSkala services_get_skala = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesSkala.class);

    public static GetServicesGroupAll services_get_group_all = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesGroupAll.class);

    public static GetServicesDaftarCatatan services_get_daftar_catatan = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesDaftarCatatan.class);



    public interface PostServices{
        @FormUrlEncoded
        @POST("login/login")
        Call<ModelLoginUser> login(
                @Field("username") String username,
                @Field("password") String password
        );


        @FormUrlEncoded
        @POST("daftartugassiswa/create")
        Call<String> create(
                @Field("siswa_id") String siswa_id,
                @Field("nama_tugas") String nama_tugas,
                @Field("kategori") String kategori,
                @Field("keterangan") String keterangan,
                @Field("tanggal_tugas") String tanggal_tugas,
                @Field("tanggal_selesai") String tanggal_selesai
        );
    }
    public interface GetServicesProfil{
        @GET("profilsiswa/{id}")
        Call<UserProfilSiswa> getProfile(@Path("id") int id);
    }
    public interface GetServicesHistori{
        @GET("historisiswa/{id}")
        Call<UserHistoriSiswa> getHistori(@Path("id") int id);
    }

    public interface GetServicesSkala{
        @GET("skalaprioritas/{id}")
        Call<ModelSkalaPrioritas> getSkala(@Path("id") int id);
    }
    public interface GetServicesGroupAll{
        @GET("siswagrup/{id}")
        Call<ModelGroupAll> getGroupAll(@Path("id") int id);
    }
    public interface GetServicesDaftarCatatan{
        @GET("daftartugaspendidikansiswa/{id}")
        Call<ModelDaftarCatatan> getDaftarCatatan(@Path("id") int id);
    }
    public interface PostServicesCatatan{
        @FormUrlEncoded
        @POST("daftartugassiswa/create")
        Call<ModelCreateTugas> create(
                @Field("nama_tugas") String nama_tugas,
                @Field("kategori") String kategori,
                @Field("keterangan") String keterangan,
                @Field("tanggal_tugas") String tanggal_tugas,
                @Field("tanggal_selesai") String tanggal_selesai

        );
    }
}
