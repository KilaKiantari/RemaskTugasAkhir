package com.example.asus_desktop.remask;

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

/**
 * Created by Asus-Desktop on 5/16/2018.
 */

public class MyGroupAdapter extends RecyclerView.Adapter<MyGroupAdapter.MahasiswaViewHolder> {


    private ArrayList<Result> result;
    private Context mContext;

    public MyGroupAdapter(Context mContext,ArrayList<Result> results)
    {
        this.mContext = mContext;
        this.result = results;
    }

    @Override
    public MyGroupAdapter.MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_mygroup, parent, false);
        return new MyGroupAdapter.MahasiswaViewHolder(view);
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNamaGroup, txtNamaGuru,txtNamaMatpel,txtSekolah;
        public ImageView overflow;
        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNamaGroup = (TextView) itemView.findViewById(R.id.txt_nama_grup);
            txtNamaGuru = (TextView) itemView.findViewById(R.id.txt_nama_guru);
            txtNamaMatpel = (TextView) itemView.findViewById(R.id.txt_nama_matpel);
            txtSekolah= (TextView) itemView.findViewById(R.id.txt_nama_sekolah);
            overflow = (ImageView) itemView.findViewById(R.id.overflow);



        }
    }

    @Override
    public void onBindViewHolder(final MyGroupAdapter.MahasiswaViewHolder holder, int position) {
//        holder.txtNama.setText(result.get(position).getResults().getNamaTugas());

        holder.txtNamaGroup.setText(result.get(position).getNamagroup());
        holder.txtNamaGuru.setText(result.get(position).getNamaGuru());
        holder.txtNamaMatpel.setText(result.get(position).getNamaMatpel());
        holder.txtSekolah.setText(result.get(position).getSekolah());
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
        inflater.inflate(R.menu.menu_mygroup, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyGroupAdapter.MyMenuItemClickListener());
        popup.show();

    }

    private class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override

        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_keluar:
                    Toast.makeText(mContext, "Keluar", Toast.LENGTH_SHORT).show();
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