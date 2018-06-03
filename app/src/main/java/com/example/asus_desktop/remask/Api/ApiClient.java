package com.example.asus_desktop.remask.Api;

import com.example.asus_desktop.remask.Model.ModelActionJoin;
import com.example.asus_desktop.remask.Model.ModelCreateTugas;
import com.example.asus_desktop.remask.Model.ModelDaftarCatatan;
import com.example.asus_desktop.remask.Model.ModelGroupAll;
import com.example.asus_desktop.remask.Model.ModelGroupJoined;
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
    //public static final String BASE_URL = "http://192.168.1.7/Remask/belakang/api/";
    public static final String BASE_URL = "http://kila.jagopesan.com/Remask/belakang/api/";

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

    public static GetServicesHistoriSudah services_get_hitori_sudah = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesHistoriSudah.class);

    public static GetServicesSkala services_get_skala = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesSkala.class);

    public static GetServicesGroupAll services_get_group_all = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesGroupAll.class);

    public static GetServicesDaftarCatatan services_get_daftar_catatan = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesDaftarCatatan.class);

    public static GetServicesGroupJoined services_get_group_joined = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesGroupJoined.class);



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

        @FormUrlEncoded
        @POST("daftartugassiswa/update")
        Call<String> updatetugas(
                @Path("id_tugas") String id_tugas,
                @Field("siswa_id") String siswa_id,
                @Field("nama_tugas") String nama_tugas,
                @Field("kategori") String kategori,
                @Field("keterangan") String keterangan,
                @Field("tanggal_tugas") String tanggal_tugas,
                @Field("tanggal_selesai") String tanggal_selesai

        );

        @FormUrlEncoded
        @POST("daftartugaspendidikansiswa/create")
        Call<String> creatependidikan(
                @Field("group_id") String group_id,
                @Field("siswa_id") String siswa_id,
                @Field("nama_tugas") String nama_tugas,
                @Field("kategori") String kategori,
                @Field("keterangan") String keterangan,
                @Field("tanggal_tugas") String tanggal_tugas,
                @Field("tanggal_selesai") String tanggal_selesai

        );

        @FormUrlEncoded
        @POST("siswagrup/joinedoverflow")
        Call<ModelActionJoin> join(
                @Field("namagroup") String namagroup,
                @Field("siswa_id") String siswa_id,
                @Field("guru_id") String guru_id

        );

        @FormUrlEncoded
        @POST("historisiswa/checklist/{id_tugas}")
        Call<ModelActionJoin> checklist(
                @Path("id_tugas") String id_tugas,
                @Field("status_tugas") String status_tugas,
                @Field("tanggal_selesai") String tanggal_selesai
                );
    }
    public interface GetServicesProfil{
        @GET("profilsiswa/{id}")
        Call<UserProfilSiswa> getProfile(@Path("id") String id);
    }
    public interface GetServicesHistori{
        @GET("historisiswa/index/{id}")
        Call<UserHistoriSiswa> getHistori(@Path("id") String id);
    }

    public interface GetServicesHistoriSudah{
        @GET("historisiswa/indexsudah/{id}")
        Call<UserHistoriSiswa> getHistoriSudah(@Path("id") int id);
    }


    public interface GetServicesSkala{
        @GET("skalaprioritas/{id}")
        Call<ModelSkalaPrioritas> getSkala(@Path("id") int id);
    }
  /*  public interface GetServicesGroupAll{
        @GET("siswagrup/index")
        Call<ModelGroupAll> getGroupAll();
    }
    */

    public interface GetServicesGroupAll{
        @GET("siswagrup/index/{id}")
        Call<ModelGroupAll> getGroupAll(@Path("id") int id);
    }
    public interface GetServicesDaftarCatatan{
        @GET("daftartugassiswa/index/{id}")
        Call<ModelDaftarCatatan> getDaftarCatatan(@Path("id") String id);

//        @GET("daftartugaspendidikansiswa/index?id={id}_{tanggal_tugas}")
//        Call<ModelDaftarCatatan> getDaftarCatatan(
//                @Path("id") int id,
//                @Path("tanggal_tugas") String tanggal_tugas
//        );

    }

    public interface GetServicesGroupJoined{
        @GET("siswagrup/joined/{id}")
        Call<ModelGroupJoined> getGroupJoined(@Path("id") int id);
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
