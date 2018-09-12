package com.example.asus_desktop.remask;

/**
 * Created by Asus-Desktop on 4/17/2018.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelDelete;
import com.example.asus_desktop.remask.Model.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class DaftarAdapter extends RecyclerView.Adapter<DaftarAdapter.MahasiswaViewHolder> {

    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_catatan, parent, false);
        return new MahasiswaViewHolder(view);
    }


    private ArrayList<Result> result;
    private Context mContext;
    private String id_tugas;
    private String siswa_id;
    private String kat;
    private TextView txtNamaTugas, txtTgl, txtKeterangan, txtKategori;
    private String nama_tugas;
    private String keterangan;
    private String tgl;
    public Result infoData;


    public class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNamaTugas, txtTgl, txtKeterangan, txtKategori;
        public ImageView overflow;

        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNamaTugas = (TextView) itemView.findViewById(R.id.txt_nama_tugas);
            txtTgl = (TextView) itemView.findViewById(R.id.tgl_tugas);
            txtKeterangan = (TextView) itemView.findViewById(R.id.txt_keterangan);
            txtKategori = (TextView) itemView.findViewById(R.id.id_kategori);
            overflow = (ImageView) itemView.findViewById(R.id.overflow);

        }

    }

    public DaftarAdapter(Context mContext, ArrayList<Result> results) {
        this.mContext = mContext;
        this.result = results;

    }


    public void onBindViewHolder(final MahasiswaViewHolder holder, final int position) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("Remask", MODE_PRIVATE);
        siswa_id = sharedPreferences.getString("siswa_id", "");

        holder.txtNamaTugas.setText(result.get(position).getNamaTugas());
        holder.txtKeterangan.setText(result.get(position).getKeterangan());
        holder.txtTgl.setText(result.get(position).getTanggalTugas());


        switch (result.get(position).getKategori()) {
            case "1":
                holder.txtKategori.setText("lain-lain");
                break;
            case "2":
                holder.txtKategori.setText("organisasi");
                break;
            case "3":
                holder.txtKategori.setText("pendidikan");
                break;
        }


        holder.overflow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showPopupMenu(holder.overflow);
                nama_tugas = result.get(position).getNamaTugas();
                id_tugas = result.get(position).getIdTugas();
                kat = result.get(position).getKategori();
                keterangan = result.get(position).getKeterangan();
                tgl = result.get(position).getTanggalTugas();
                infoData = result.get(position);
//                nama_tugas = txtNamaTugas.getText().toString();
//                keterangan = txtKeterangan.getText().toString();

            }
        });
    }


    private void showPopupMenu(View view) {

        //inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_catatan, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();

    }

    //Click listener for popup menu item

    private class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override

        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_edit:
                    if (kat.equals("3")) {
                        Intent i = new Intent(mContext, Buat_Catatan_Pendidikan.class);
                        mContext.startActivity(i);
                    } else if (kat.equals("2")) {
                        Intent i = new Intent(mContext, BuatCatatan.class);
                        i.putExtra("id_tugas", id_tugas);
                        i.putExtra("nama_tugas", nama_tugas);
                        i.putExtra("keterangan", keterangan);
                        i.putExtra("tgl", tgl);
                        mContext.startActivity(i);
                        //    Toast.makeText(mContext, ""+id_tugas+""+nama_tugas+""+keterangan, Toast.LENGTH_SHORT).show();
                    } else if (kat.equals("1")) {
                        Intent i = new Intent(mContext, BuatCatatanLain.class);
                        mContext.startActivity(i);
                    }
                    Toast.makeText(mContext, "" + kat, Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_delete:
                    Toast.makeText(mContext, ""+id_tugas, Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                    alertDialogBuilder.setTitle("Peringatan");
                    alertDialogBuilder
                            .setMessage("Apakah Anda yakin ingin mengapus data ini?")
                            .setCancelable(false)
                            .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ApiClient.services_get_delete.getDelete(id_tugas).enqueue(new Callback<ModelDelete>() {
                                        @Override
                                        public void onResponse(Call<ModelDelete> call, Response<ModelDelete> response) {
                                            Log.e("Response Delete " , "Code : " + response.code());
                                            if(response.isSuccessful()){
                                                Toast.makeText(mContext, "Tugas Anda Berhasil Dihapus ", Toast.LENGTH_SHORT).show();
                                                removeItem(infoData);
                                            }else {
                                                Toast.makeText(mContext, ""+response.body().getResults(), Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ModelDelete> call, Throwable t) {
                                            Toast.makeText(mContext, ""+t, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                            }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    break;
            default:}
        return false;
        }
    }
    private void removeItem(Result infoData) {

        int currPosition = result.indexOf(infoData);
        result.remove(currPosition);
        notifyItemRemoved(currPosition);
    }



    @Override
        public int getItemCount() {

            return result.size();
        }

    }
