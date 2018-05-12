package com.example.asus_desktop.remask;

/**
 * Created by Asus-Desktop on 4/17/2018.
 */

import android.content.Context;
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



public class DaftarAdapter extends RecyclerView.Adapter<DaftarAdapter.MahasiswaViewHolder> {

    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_catatan, parent, false);
        return new MahasiswaViewHolder(view);
    }


    private ArrayList<Result> result;
    private Context mContext;


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


    public void onBindViewHolder(final MahasiswaViewHolder holder, int position) {
        holder.txtNamaTugas.setText(result.get(position).getNamaTugas());
        holder.txtKeterangan.setText(result.get(position).getKeterangan());
        holder.txtTgl.setText(result.get(position).getTanggalTugas());
        holder.txtKategori.setText(result.get(position).getKategori());
        holder.overflow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showPopupMenu(holder.overflow);
                
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
                    Toast.makeText(mContext, "Edit", Toast.LENGTH_SHORT).show();
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