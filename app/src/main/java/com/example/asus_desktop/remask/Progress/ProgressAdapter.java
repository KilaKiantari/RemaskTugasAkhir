package com.example.asus_desktop.remask.Progress;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus_desktop.remask.Model.ModelActionJoin;
import com.example.asus_desktop.remask.Model.Result;
import com.example.asus_desktop.remask.R;

import java.util.ArrayList;

/**
 * Created by Asus-Desktop on 6/7/2018.
 */

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.MahasiswaViewHolder> {


    private ArrayList<Result> result;
    private Context mContext;
    // private String tgl_selesai;
    private String id_progress;
    private String status_progress;
    private String tanggal_selesai;
    private String nama_progress;
    private String siswa_id;
    private String ketegori;
    private String keterangan;
    private String tgl_selesai;
    private String author;


    ModelActionJoin modelActionJoin;

    public ProgressAdapter(Context mContext,ArrayList<Result> results)
    {

        this.result = results;
        this.mContext = mContext;
    }

    @Override
    public ProgressAdapter.MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_progress, parent, false);
        return new ProgressAdapter.MahasiswaViewHolder(view);
    }


    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView tittle,coba;
        private CheckBox chk_item;


        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            tittle = (TextView) itemView.findViewById(R.id.title);
            chk_item = (CheckBox) itemView.findViewById(R.id.chk_item);
          //  coba = (TextView) itemView.findViewById(R.id.coba);

        }
    }
    @Override
    public void onBindViewHolder(final ProgressAdapter.MahasiswaViewHolder holder, final int position) {

        holder.tittle.setText(result.get(position).getNamaProgress());
        //holder.coba.setText(result.get(position).getIdProgress());
        holder.chk_item.setChecked(result.get(position).isSelected());
        holder.chk_item.setTag(result.get(position));
        holder.chk_item.setOnClickListener(new View.OnClickListener() {
        //@Override
        public void onClick(View v) {
            CheckBox cb = (CheckBox) v;
            Result contact = (Result) cb.getTag();
            contact.setSelected(cb.isChecked());
            result.get(position).setSelected(cb.isChecked());
            nama_progress = result.get(position).getNamaProgress();
            id_progress = result.get(position).getIdProgress();
            tgl_selesai = result.get(position).getTglSelesai();
            status_progress = result.get(position).getStatusProgress();

            if(cb.isChecked()) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Penting !")
                        .setMessage("Apakah anda yakin telah menyelesaikan tugas " +nama_progress+ "?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(mContext, "" + nama_progress, Toast.LENGTH_SHORT).show();
                                Toast.makeText(mContext, "" + tgl_selesai, Toast.LENGTH_SHORT).show();
                                Toast.makeText(mContext, ""+ id_progress,Toast.LENGTH_SHORT).show();

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