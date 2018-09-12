package com.example.asus_desktop.remask.Api;

import com.example.asus_desktop.remask.Model.ModelActionJoin;
import com.example.asus_desktop.remask.Model.ModelCreateTugas;
import com.example.asus_desktop.remask.Model.ModelDaftarCatatan;
import com.example.asus_desktop.remask.Model.ModelDelete;
import com.example.asus_desktop.remask.Model.ModelGrafikKerajinan;
import com.example.asus_desktop.remask.Model.ModelGrafikKeterangan;
import com.example.asus_desktop.remask.Model.ModelGrafikKeteranganLain;
import com.example.asus_desktop.remask.Model.ModelGrafikKeteranganOrganisasi;
import com.example.asus_desktop.remask.Model.ModelGrafikProgress;
import com.example.asus_desktop.remask.Model.ModelGroupAll;
import com.example.asus_desktop.remask.Model.ModelGroupJoined;
import com.example.asus_desktop.remask.Model.ModelLoginUser;
import com.example.asus_desktop.remask.Model.ModelProgress;
import com.example.asus_desktop.remask.Model.ModelProgressHistori;
import com.example.asus_desktop.remask.Model.ModelRegister;
import com.example.asus_desktop.remask.Model.ModelRegisterNext;
import com.example.asus_desktop.remask.Model.ModelSkalaPrioritas;
import com.example.asus_desktop.remask.Model.UserHistoriSiswa;
import com.example.asus_desktop.remask.Model.UserProfilSiswa;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Asus-Desktop on 5/6/2018.
 */

public class ApiClient {
    //public static final String BASE_URL = "http://192.168.1.7/Remask/belakang/api/";
    public static final String BASE_URL = "http://kila.jagopesan.com/Remask/belakang/api/";
    //public static final String BASE_URL = "http://192.168.43.107/Remask/belakang/api/";

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

    public static GetGrafikProgress services_get_grafik_progress = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetGrafikProgress.class);

    public static GetGrafikProgressBelum services_get_grafik_progress_belum = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetGrafikProgressBelum.class);

    public static GetGrafikKerajinanOrganisasi services_get_grafik_organisasi = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetGrafikKerajinanOrganisasi.class);

    public static GetGrafikKerajinanPendidikan services_get_grafik_pendidikan = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetGrafikKerajinanPendidikan.class);

    public static GetGrafikKerajinanLain services_get_grafik_lain = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetGrafikKerajinanLain.class);

    public static GetServicesProgress services_get_progress = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesProgress.class);

    public static GetServicesProgressudah services_get_progressudah = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesProgressudah.class);

    public static GetServicesSkala services_get_skala = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesSkala.class);

    public static GetServicesSkalaBaru services_get_skala_baru = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesSkalaBaru.class);


    public static GetServicesGroupAll services_get_group_all = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesGroupAll.class);

    public static GetSearchGroup services_get_search_group = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetSearchGroup.class);

    public static GetServicesDaftarCatatan services_get_daftar_catatan = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesDaftarCatatan.class);

    public static GetServicesGroupJoined services_get_group_joined = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesGroupJoined.class);


    public static GetServicesGroupJoinedSpinner services_get_group_joined_spinner = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetServicesGroupJoinedSpinner.class);

    public static GetGrafikKeterangan services_get_grafik_keterangan_pendidikan = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetGrafikKeterangan.class);

    public static GetGrafikKeteranganOr services_get_grafik_keterangan_organisasi = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetGrafikKeteranganOr.class);

    public static GetGrafikKeteranganLain services_get_grafik_keterangan_lain = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetGrafikKeteranganLain.class);

    public static GetDelete services_get_delete = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.GetDelete.class);



    public interface PostServices {

        @FormUrlEncoded
        @POST("login/login")
        Call<ModelLoginUser> login(
                @Field("username") String username,
                @Field("password") String password
        );

        @FormUrlEncoded
        @POST("signupsiswa/signup")
        Call<ModelRegister> register(
                @Field("nama_lengkap") String nama_lengkap,
                @Field("sekolah") String sekolah

        );
        @FormUrlEncoded
        @POST("signupsiswa/signupnext")
        Call<ModelRegisterNext> registernext(
                @Field("username") String username,
                @Field("email") String email,
                @Field("password") String password,
                @Query("id") String id

        );


        @FormUrlEncoded
        @POST("daftartugassiswa/create")
        Call<ModelCreateTugas> create(
                @Field("siswa_id") String siswa_id,
                @Field("nama_tugas") String nama_tugas,
                @Field("kategori") String kategori,
                @Field("keterangan") String keterangan,
                @Field("tanggal_tugas") String tanggal_tugas,
                @Field("tanggal_selesai") String tanggal_selesai

        );


        @FormUrlEncoded
        @POST("daftartugassiswa/createprogress")
        Call<ModelProgress> createprogresstambah(
                @Field("nama_progress") String namaprogress,
                @Field("siswa_id") String siswa_id,
                @Field("tgl_selesai") String tgl_selesai,
                @Query("id") String id

        );


        @FormUrlEncoded
        @POST("daftartugassiswa/update/id_tugas")
        Call<ModelCreateTugas> updatetugas(
                @Path("id_tugas") Integer id_tugas,
                @Field("siswa_id") String siswa_id,
                @Field("nama_tugas") String nama_tugas,
                @Field("keterangan") String keterangan
        );

        @FormUrlEncoded
        @POST("daftartugaspendidikansiswa/create")
        Call<ModelCreateTugas> creatependidikan(
                @Field("group_id") ArrayList<String> group_id,
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

        @FormUrlEncoded
        @POST("progress/checklistprogress/{id_progress}")
        Call<ModelActionJoin> checklistprogress(
                @Path("id_progress") String id_progress,
                @Field("status_progress") String status_progress,
                @Field("tgl_selesai") String tgl_selesai
        );

//        @FormUrlEncoded
//        @POST("gurugroup/cari/{id}")
//        Call<ModelGroupAll> getSearchGroup(
//                @Field("id") String id
//        );

    }

    public interface GetServicesProfil {
        @GET("profilsiswa/{id}")
        Call<UserProfilSiswa> getProfile(@Path("id") String id);
    }

    public interface GetDelete{
        @GET("daftartugassiswa/delete/{id}")
        Call<ModelDelete> getDelete(@Path("id") String id);
    }

    public interface GetServicesHistori {
        @GET("historisiswa/index/{id}")
        Call<UserHistoriSiswa> getHistori(@Path("id") String id);
    }

    public interface GetServicesHistoriSudah {
        @GET("historisiswa/indexsudah/{id}")
        Call<UserHistoriSiswa> getHistoriSudah(@Path("id") String id);
    }

    public interface GetServicesProgress {
        @GET("daftartugassiswa/indexprogress/{id}")
        Call<ModelProgressHistori> getProgress(@Path("id") int id);
    }

    public interface GetServicesProgressudah {
        @GET("daftartugassiswa/indexprogressudah/{tugas_id}")
        Call<ModelProgressHistori> getProgressudah(@Path("tugas_id") int tugas_id);
    }

    public interface GetServicesSkala {
        @GET("skalaprioritas/index/{id}")
        Call<ModelSkalaPrioritas> getSkala(@Path("id") String id);
    }

    public interface GetServicesSkalaBaru {
        @GET("skalaprioritas/indexbaru/{id}")
        Call<ModelSkalaPrioritas> getSkalaBaru(@Path("id") String id);
    }
  /*  public interface GetServicesGroupAll{
        @GET("siswagrup/index")
        Call<ModelGroupAll> getGroupAll();
    }
    */

    public interface GetServicesGroupAll {
        @GET("siswagrup/index/{id}")
        Call<ModelGroupAll> getGroupAll(@Path("id") String id);
    }

    public interface GetServicesDaftarCatatan {
        @GET("daftartugassiswa/index/{id}")
        Call<ModelDaftarCatatan> getDaftarCatatan(@Path("id") String id);
    }
    public interface GetSearchGroup {
        @GET("gurugroup/cari")
        Call<ModelGroupAll> getSearchGroup(@Query("id") String id);
    }

//        @GET("daftartugaspendidikansiswa/index?id={id}_{tanggal_tugas}")
//        Call<ModelDaftarCatatan> getDaftarCatatan(
//                @Path("id") int id,
//                @Path("tanggal_tugas") String tanggal_tugas
//        );

    public interface GetGrafikProgress {
        @GET("grafik/grafikprogress/{id}")
        Call<ModelGrafikProgress> getGrafikProgress(@Path("id") String id);
    }

    public interface GetGrafikKeterangan {
        @GET("grafikketerangan/grafikpendidikan/{id}")
        Call<ModelGrafikKeterangan> getGrafikket(@Path("id") String id);
    }


    public interface GetGrafikKeteranganOr {
        @GET("grafikketerangan/grafikorganisasi/{id}")
        Call<ModelGrafikKeteranganOrganisasi> getGrafikketor(@Path("id") String id);
    }

    public interface GetGrafikKeteranganLain {
        @GET("grafikketerangan/grafiklainlain/{id}")
        Call<ModelGrafikKeteranganLain> getGrafikketla(@Path("id") String id);
    }


    public interface GetGrafikProgressBelum {
        @GET("grafik/grafikprogressbelum/{id}")
        Call<ModelGrafikProgress> getGrafikProgressBelum(@Path("id") String id);
    }

    public interface GetGrafikKerajinanOrganisasi {
        @GET("grafik/grafikorganisasiselisih/{id}")
        Call<ModelGrafikKerajinan> getGrafikKerajinanOrganisasi(@Path("id") String id);
    }

    public interface GetGrafikKerajinanPendidikan {
        @GET("grafik/grafikpendidikanselisih/{id}")
        Call<ModelGrafikKerajinan> getGrafikKerajinanPendidikan(@Path("id") String id);
    }

    public interface GetGrafikKerajinanLain {
        @GET("grafik/grafiklainselisih/{id}")
        Call<ModelGrafikKerajinan> getGrafikKerajinanLain(@Path("id") String id);
    }

    public interface GetServicesGroupJoined {
        @GET("siswagrup/joined/{id}")
        Call<ModelGroupJoined> getGroupJoined(@Path("id") String id);
    }

    public interface GetServicesGroupJoinedSpinner {
        @GET("siswagrup/spinnergrup/{id}")
        Call<ModelGroupJoined> getGroupJoinedSpinner(@Path("id") String id);
    }


    public interface PostServicesCatatan {
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

