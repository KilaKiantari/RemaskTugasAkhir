package com.example.asus_desktop.remask.HistoriTugas;

/**
 * Created by Asus-Desktop on 4/12/2018.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.asus_desktop.remask.Model.ModelActionJoin;
import com.example.asus_desktop.remask.Model.Result;
import com.example.asus_desktop.remask.Progress.MainActivityProgress;
import com.example.asus_desktop.remask.R;

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
    private String tugas_id;
    private AdapterView.OnItemClickListener listener;

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
        private CardView cardView;
        private AdapterView.OnItemClickListener listener;

        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama_tugas);
            txtTgl = (TextView) itemView.findViewById(R.id.tgl_tugas);
            txtTglSelesai = (TextView) itemView.findViewById(R.id.tgl_tugas_selesai);
            checkbox = (CheckBox) itemView.findViewById(R.id.id_checkBox);
            cardView = (CardView) itemView.findViewById(R.id.card_view);


        }
    }
    @Override
    public void onBindViewHolder(final MahasiswaViewHolder holder,final int position) {
//        holder.txtNama.setText(result.get(position).getResults().getNamaTugas());

        holder.txtNama.setText(result.get(position).getNamaTugas());
        holder.txtNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(mContext, MainActivityProgress.class);
               i.putExtra("Extra", "ProgressTugas");
               i.putExtra("id_tugas", result.get(position).getIdTugas());
               mContext.startActivity(i);



              //  i.putExtra("nama_progress",result.get(position).getNamaProgress());
          //      i.putExtra("tugas_id", result.get(position).getIdTugas());
             //   Toast.makeText(mContext, ""+result.get(position).getIdTugas(), Toast.LENGTH_SHORT).show();

            }
        });
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