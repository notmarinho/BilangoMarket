package com.example.mateus.bilangomarket.DATA;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AjudanteBD  extends SQLiteOpenHelper {
    public AjudanteBD(Context context){
        super(context, Contrato.DB_NAME, null, Contrato.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContratoUsuario.SQL_CRIAR_TABELA_USUARIO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ContratoUsuario.SQL_DELETAR_TABELA_USUARIO);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}
