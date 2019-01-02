package com.example.mateus.multiplestables.DATA;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static final String TAG = "DBHelper";

    //Tabela dos Usuarios
    public static final String TABELA_USUARIO = "USUARIO";
    public static final String COLUNA_USUARIO_ID      = "_id";
    public static final String COLUNA_USUARIO_NOME = "NOME";
    public static final String COLUNA_USUARIO_EMAIL = "EMAIL";
    public static final String COLUNA_USUARIO_SENHA = "SENHA";
    public static final String COLUNA_USUARIO_STATUS = "STATUS";


    //Tabela dos Anuncios
    public static final String TABELA_ANUNCIO = "ANUNCIO";
    public static final String COLUNA_ANUNCIO_ID        = "_id";
    public static final String COLUNA_ANUNCIO_NOME      = "NOME";
    public static final String COLUNA_ANUNCIO_PRECO     = "PRECO";
    public static final String COLUNA_ANUNCIO_DESCRICAO = "DESCRICAO";
    public static final String COLUNA_ANUNCIO_DONOID    = "DONOID";

    private static final String NOME_BANCO = "bilango.db";
    private static final int VERSAO_BANCO = 1;

    private static final String SQL_CREATE_TABLE_USUARIO = "CREATE TABLE " + TABELA_USUARIO + "("
            + COLUNA_USUARIO_ID     + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUNA_USUARIO_NOME   + " TEXT NOT NULL, "
            + COLUNA_USUARIO_EMAIL  + " TEXT NOT NULL, "
            + COLUNA_USUARIO_SENHA  + " TEXT NOT NULL, "
            + COLUNA_USUARIO_STATUS + " INTEGER NOT NULL "
            + ");";

    private static final String SQL_CREATE_TABLE_ANUNCIO = "CREATE TABLE " + TABELA_ANUNCIO + "("
            + COLUNA_ANUNCIO_ID     + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUNA_ANUNCIO_NOME   + " TEXT NOT NULL, "
            + COLUNA_ANUNCIO_PRECO  + " REAL NOT NULL, "
            + COLUNA_ANUNCIO_DESCRICAO  + " TEXT NOT NULL, "
            + COLUNA_ANUNCIO_DONOID + " INTEGER NOT NULL "
            + ");";


    public DBHelper(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_USUARIO);
        db.execSQL(SQL_CREATE_TABLE_ANUNCIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading the database from version " + oldVersion + " to " + newVersion);

        //Apagando o banco
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_ANUNCIO);

        onCreate(db);
    }
}
