package com.greenproject.carteiradeclientes2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UfmapOpenHelper extends SQLiteOpenHelper {

    public UfmapOpenHelper(Context context) {
        super(context, "UFMap", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptDLL.getCreateTableCliente());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
