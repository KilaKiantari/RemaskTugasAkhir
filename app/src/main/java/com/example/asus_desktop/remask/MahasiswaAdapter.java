package com.example.asus_desktop.remask;

/**
 * Created by Asus-Desktop on 4/12/2018.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus_desktop.remask.Model.Result;

import java.util.ArrayList;


public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {


    private ArrayList<Result> result;

    public MahasiswaAdapter(ArrayList<Result> results)
    {
        this.result = results;
    }

    @Override
    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_row, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MahasiswaViewHolder holder, int position) {
//        holder.txtNama.setText(result.get(position).getResults().getNamaTugas());

        holder.txtNama.setText(result.get(position).getNamaTugas());
        holder.txtTgl.setText(result.get(position).getTanggalTugas());
        holder.txtTglSelesai.setText(result.get(position).getTanggalSelesai());


    }

    @Override
    public int getItemCount() {

        return result.size();
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtTgl, txtTglSelesai, txtKeterangan;

        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama_tugas);
            txtTgl = (TextView) itemView.findViewById(R.id.tgl_tugas);
            txtTglSelesai = (TextView) itemView.findViewById(R.id.tgl_tugas_selesai);


        }
    }
}