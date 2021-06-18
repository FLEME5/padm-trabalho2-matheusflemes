package com.padmtrabalho2.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.padmtrabalho2.dao.personagem.SQLitePersonagemMetaDados;

public class SQLiteBDHelper extends SQLiteOpenHelper implements SQLiteDadosBanco, SQLitePersonagemMetaDados {

    public SQLiteBDHelper(Context context) {
        super(context, SQLiteDadosBanco.DATABASE, null, SQLiteDadosBanco.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLitePersonagemMetaDados.METADADOSCREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
/*        String updateVersao2 = "ALTER TABLE " + SQLitePersonagemMetaDados.TABLE + " ADD COLUMN EMAIL TEXT";
        String updateVersao3 = "ALTER TABLE " + SQLitePersonagemMetaDados.TABLE + " ADD COLUMN FONE TEXT";
        if (oldVersion == 1) {
            //lógica de atualização da v1 para v2
            //db.execSQL(updateVersao2);
            //db.execSQL(updateVersao3);
        } else {
            if (oldVersion == 2) {
                //lógica de atualização da v2 para v3
                //db.execSQL(updateVersao3);
            }
        }*/
    }
}
