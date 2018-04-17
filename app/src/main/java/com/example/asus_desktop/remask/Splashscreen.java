package com.example.asus_desktop.remask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Asus-Desktop on 6/13/2017.
 */

public class Splashscreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        Thread timer = new Thread() {
            public void run() {
                try {
                    //berapa lama splash screen muncul dalam satuan milisecond
                    sleep(2000);

                } catch (Exception e) {
                    e.printStackTrace();

                } finally {

                    //activity yang akan dijalankan setelah splash screen selesai
                    Intent main = new Intent(Splashscreen.this, Login.class);
                    startActivity(main);

                    //sertakan method finish agar splash screen tidak bisa diakses kembali dgngan tombol back
                    finish();
                }
            }

            ;
        };
        timer.start();
    }
}
