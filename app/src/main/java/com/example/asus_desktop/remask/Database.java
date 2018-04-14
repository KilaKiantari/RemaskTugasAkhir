package com.example.asus_desktop.remask;

/**
 * Created by Asus-Desktop on 3/3/2018.
 */

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
public class Database extends SQLiteAssetHelper {
    private static final String DATABASE_NAMES = "remask";
    private static final int DATABASE_VERSION = 3;
    public Database(Context context) {
        super(context, DATABASE_NAMES, null, DATABASE_VERSION);
    }
}