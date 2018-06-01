package com.example.asus_desktop.remask;

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

import com.example.asus_desktop.remask.Api.ApiClient;
import com.example.asus_desktop.remask.Model.ModelActionJoin;
import com.example.asus_desktop.remask.Model.ModelGroupAll;
import com.example.asus_desktop.remask.Model.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Asus-Desktop on 5/5/2018.
 */

public class JoinGroupAdapter extends RecyclerView.Adapter<JoinGroupAdapter.MahasiswaViewHolder> {


    private ArrayList<Result> result;
    private Context mContext;
    private String id_group;
    private String namagroup;
    private String guru_id;
    private String siswa_id;
    ModelGroupAll modelGroupAll;


    public JoinGroupAdapter(Context mContext,ArrayList<Result> results)
    {
        this.mContext = mContext;
        this.result = results;
    }



    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_joingroup, parent, false);
        return new MahasiswaViewHolder(view);

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



    public void onBindViewHolder(final MahasiswaViewHolder holder, final int position) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("Remask", MODE_PRIVATE);
        siswa_id = sharedPreferences.getString("siswa_id","");

        holder.txtNamaGroup.setText(result.get(position).getNamagroup());
        holder.txtNamaGuru.setText(result.get(position).getNamaGuru());
        holder.txtNamaMatpel.setText(result.get(position).getNamaMatpel());
        holder.txtSekolah.setText(result.get(position).getSekolah());
        holder.overflow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                namagroup = result.get(position).getNamagroup();
                guru_id = result.get(position).getGuruId();
                showPopupMenu(holder.overflow);

            }
        });
    }

    private void showPopupMenu(View view) {

        //inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_group, popup.getMenu());
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
                case R.id.action_join:
                    ApiClient.services_post.join(
                            namagroup,
                            siswa_id ,
                            guru_id) .enqueue(new Callback<ModelActionJoin>() {
                        @Override
                        public void onResponse(Call<ModelActionJoin> call, Response<ModelActionJoin> response) {
                            if(response.isSuccessful()) {
                             //   siswa_id = response.body().getSiswaId();
                              //  namagroup = response.body().getNamagroup();
                              //  guru_id = response.body().getGuruId();
                                 Toast.makeText(mContext, ""+response.body().getStatus(),Toast.LENGTH_SHORT).show();
                               // Toast.makeText(mContext, "" + siswa_id, Toast.LENGTH_SHORT).show();
                                // id_guru = response.body().getIdGuru();
                            }else{
                                Toast.makeText(mContext, "SALAH", Toast.LENGTH_SHORT).show();
                            }
//
                           // Toast.makeText(mContext, ""+namagroup, Toast.LENGTH_SHORT).show();


                           // progressDialog.dismiss();

                        }

                        @Override
                        public void onFailure(Call<ModelActionJoin> call, Throwable t) {
                            Toast.makeText(mContext, "" +t, Toast.LENGTH_SHORT).show();
                        }
                    });
                    Toast.makeText(mContext, ""+namagroup,Toast.LENGTH_SHORT).show();
                    Toast.makeText(mContext, ""+guru_id,Toast.LENGTH_SHORT).show();
                    Toast.makeText(mContext, "Join",Toast.LENGTH_SHORT).show();
                    Toast.makeText(mContext, ""+siswa_id,Toast.LENGTH_SHORT).show();
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