package com.example.asus_desktop.remask;

/**
 * Created by Asus-Desktop on 4/17/2018.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus_desktop.remask.Model.Result;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class DaftarAdapter extends RecyclerView.Adapter<DaftarAdapter.MahasiswaViewHolder> {

    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_catatan, parent, false);
        return new MahasiswaViewHolder(view);
    }


    private ArrayList<Result> result;
    private Context mContext;
    private String nama_tugas;
    private String id_tugas;
    private String siswa_id;


    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
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

    public DaftarAdapter(Context mContext,ArrayList<Result> results) {
        this.mContext = mContext;
        this.result = results;

    }


    public void onBindViewHolder(final MahasiswaViewHolder holder, final int position) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("Remask", MODE_PRIVATE);
        siswa_id = sharedPreferences.getString("siswa_id","");

        holder.txtNamaTugas.setText(result.get(position).getNamaTugas());
        holder.txtKeterangan.setText(result.get(position).getKeterangan());
        holder.txtTgl.setText(result.get(position).getTanggalTugas());

        switch (result.get(position).getKategori()){
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
//                   ApiClient.services_post.updatetugas(
//                            id_tugas,
//                            siswa_id,
//                            txt
//                            guru_id) .enqueue(new Callback<ModelActionJoin>() {
//                        @Override
//                        public void onResponse(Call<ModelActionJoin> call, Response<ModelActionJoin> response) {
//                            if(response.isSuccessful()) {
//                                //   siswa_id = response.body().getSiswaId();
//                                //  namagroup = response.body().getNamagroup();
//                                //  guru_id = response.body().getGuruId();
//                                Toast.makeText(mContext, "Anda berhasil Join Group "+namagroup,Toast.LENGTH_SHORT).show();
//                                // Toast.makeText(mContext, "" + siswa_id, Toast.LENGTH_SHORT).show();
//                                // id_guru = response.body().getIdGuru();
//                            }else{
//                                Toast.makeText(mContext, "SALAH", Toast.LENGTH_SHORT).show();
//                            }
////
//                            // Toast.makeText(mContext, ""+namagroup, Toast.LENGTH_SHORT).show();
//
//
//                            // progressDialog.dismiss();
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<ModelActionJoin> call, Throwable t) {
//                            Toast.makeText(mContext, "" +t, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    Toast.makeText(mContext, ""+id_tugas, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(mContext, ""+nama_tugas, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(mContext, "Edit", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_delete:
                    Toast.makeText(mContext, "Delete", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }return false;

        }
    }
    @Override
    public int getItemCount() {

        return result.size();
    }

}