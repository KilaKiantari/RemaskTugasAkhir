package com.example.asus_desktop.remask;

/**
 * Created by Asus-Desktop on 4/12/2018.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.asus_desktop.remask.Model.ModelActionJoin;
import com.example.asus_desktop.remask.Model.Result;

import java.util.ArrayList;


public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {


    private ArrayList<Result> result;
    private Context mContext;
   // private String tgl_selesai;
   private String id_tugas;
    private String status_tugas;
    private String tanggal_selesai;
    private String nama_tugas;
    private String siswa_id;
    private String ketegori;
    private String keterangan;
    private String tanggal_tugas;
    private String author;


    ModelActionJoin modelActionJoin;

    public MahasiswaAdapter(Context mContext,ArrayList<Result> results)
    {

        this.result = results;
        this.mContext = mContext;
    }

    @Override
    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_row, parent, false);
        return new MahasiswaViewHolder(view);
    }


    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtTgl, txtTglSelesai;
        private CheckBox checkbox;

        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama_tugas);
            txtTgl = (TextView) itemView.findViewById(R.id.tgl_tugas);
            txtTglSelesai = (TextView) itemView.findViewById(R.id.tgl_tugas_selesai);
            checkbox = (CheckBox) itemView.findViewById(R.id.id_checkBox);

        }
    }
    @Override
    public void onBindViewHolder(final MahasiswaViewHolder holder,final int position) {
//        holder.txtNama.setText(result.get(position).getResults().getNamaTugas());

        holder.txtNama.setText(result.get(position).getNamaTugas());
        holder.txtTgl.setText(result.get(position).getTanggalTugas());
        holder.txtTglSelesai.setText(result.get(position).getTanggalSelesai());
        holder.checkbox.setChecked(result.get(position).isSelected());
        holder.checkbox.setTag(result.get(position));
        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
               CheckBox cb = (CheckBox) v;
               Result contact = (Result) cb.getTag();
               contact.setSelected(cb.isChecked());
               result.get(position).setSelected(cb.isChecked());
               tanggal_selesai = result.get(position).getTanggalSelesai();
               status_tugas = result.get(position).getStatusTugas();
               nama_tugas = result.get(position).getNamaTugas();
               id_tugas = result.get(position).getIdTugas();
               if(cb.isChecked()) {
                   new AlertDialog.Builder(mContext)
                           .setTitle("Penting !")
                           .setMessage("Apakah anda yakin telah menyelesaikan tugas " +nama_tugas+ "?")
                           .setNegativeButton(android.R.string.no, null)
                           .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface arg0, int arg1) {

                                //   Toast.makeText(mContext, "" + nama_tugas, Toast.LENGTH_SHORT).show();
                                  // Toast.makeText(mContext, ""+ id_tugas,Toast.LENGTH_SHORT).show();
//                                     ApiClient.services_post.checklist(
//                                            id_tugas,
//                                           status_tugas,
//                                           tanggal_selesai
//                                             ) .enqueue(new Callback<ModelActionJoin>() {
//                                       @Override
//                                       public void onResponse(Call<ModelActionJoin> call, Response<ModelActionJoin> response) {
//                                           if(response.isSuccessful()) {
//                                               //nama_tugas = result.get(position).getNamaTugas();
//                                               //  namagroup = response.body().getNamagroup();
//                                               //  guru_id = response.body().getGuruId();
//                                               Toast.makeText(mContext, ""+response.body().getStatus(),Toast.LENGTH_SHORT).show();
//                                               //Log.d("name",nama_tugas);
//                                               // id_guru = response.body().getIdGuru();
//                                           }else{
//                                               Toast.makeText(mContext, "SALAH", Toast.LENGTH_SHORT).show();
//
//                                           }
////
//                                           // Toast.makeText(mContext, ""+namagroup, Toast.LENGTH_SHORT).show();
//
//
//                                           // progressDialog.dismiss();
//
//                                       }
//
//                                       @Override
//                                       public void onFailure(Call<ModelActionJoin> call, Throwable t) {
//                                           Toast.makeText(mContext, "" +t, Toast.LENGTH_SHORT).show();
//                                       }
//                                   });
                               }
                           }).create().show();


               }

                else{

                }

            }

        });

    }


    @Override
    public int getItemCount() {

        return result.size();
    }

    public ArrayList<Result> getStudentist() {

        return result;
    }

}