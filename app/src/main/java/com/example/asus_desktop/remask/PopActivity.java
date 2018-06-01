package com.example.asus_desktop.remask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by Asus-Desktop on 5/3/2018.
 */

public class PopActivity extends Activity {

    Button btn_pendidikan;
    Button btn_organisasi;

    Button btn_lain;

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);



        btn_pendidikan = (Button) findViewById(R.id.btn_pendidikan);
        btn_organisasi = (Button) findViewById(R.id.btn_organisasi);
        btn_lain = (Button) findViewById(R.id.btn_lain);

        btn_pendidikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openCreateNote = new Intent(PopActivity.this, JoinGroup.class);
                startActivity(openCreateNote);
            }
        });

        btn_organisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openCreateNote = new Intent(PopActivity.this, BuatCatatan.class);
                startActivity(openCreateNote);
            }
        });

        btn_lain.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent openCreateNote = new Intent(PopActivity.this, BuatCatatanLain.class);
                                            startActivity(openCreateNote);
                                        }
                                    });

                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);

                int width = dm.widthPixels;
                int height = dm.heightPixels;

                getWindow().setLayout((int) (width * .7), (int) (height * .6));

                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.gravity = Gravity.CENTER;
                params.x = 0;
                params.y = 0;

                getWindow().setAttributes(params);
            }


        }

