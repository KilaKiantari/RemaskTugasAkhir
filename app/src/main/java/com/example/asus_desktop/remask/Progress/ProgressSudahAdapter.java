package com.example.asus_desktop.remask.Progress;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus_desktop.remask.Model.Result;
import com.example.asus_desktop.remask.R;

import java.util.ArrayList;

/**
 * Created by Asus-Desktop on 6/11/2018.
 */

public class ProgressSudahAdapter extends RecyclerView.Adapter<ProgressSudahAdapter.MahasiswaViewHolder> {


private ArrayList<Result> result;

public ProgressSudahAdapter(ArrayList<Result> results)
        {
        this.result = results;
        }

@Override
public ProgressSudahAdapter.MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_sudah, parent, false);
        return new ProgressSudahAdapter.MahasiswaViewHolder(view);
        }

@Override
public void onBindViewHolder(ProgressSudahAdapter.MahasiswaViewHolder holder, int position) {
//        holder.txtNama.setText(result.get(position).getResults().getNamaTugas());

        holder.txtNama.setText(result.get(position).getNamaProgress());
        holder.txtTglSelesai.setText(result.get(position).getTglSelesai());
        }

@Override
public int getItemCount() {

        return result.size();
        }

public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
    private TextView txtNama,txtTglSelesai;

    public MahasiswaViewHolder(View itemView) {
        super(itemView);
        txtNama = (TextView) itemView.findViewById(R.id.txt_nama_tugas);
        txtTglSelesai = (TextView) itemView.findViewById(R.id.tgl_tugas_selesai);

        }
    }
}