package com.example.mateus.bilangomarket.DATA;

import android.provider.BaseColumns;

public class ContratoUsuario implements BaseColumns {

    private ContratoUsuario(){}

    public static final String NOME_TABELA     = "USUARIO";
    public static final String COLUNA_NOME     = "NOME";
    public static final String COLUNA_EMAIL    = "EMAIL";
    public static final String COLUNA_SENHA    = "SENHA";
    public static final String COLUNA_ANUNCIOS = "ANUNCIO";
    public static final String COLUNA_STATUS   = "STATUS";

    public static final String SQL_CRIAR_TABELA_USUARIO =
            "CREATE TABLE "+ ContratoUsuario.NOME_TABELA+ " ("+
            ContratoUsuario._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ContratoUsuario.COLUNA_NOME + " TEXT,"+
            ContratoUsuario.COLUNA_EMAIL + " TEXT," +
            ContratoUsuario.COLUNA_SENHA + " TEXT," +
            ContratoUsuario.COLUNA_ANUNCIOS + " INTEGER," +
            ContratoUsuario.COLUNA_STATUS + " INTEGER)";

    public static final String SQL_DELETAR_TABELA_USUARIO =
            "DROP TABLE IF EXISTS " + ContratoUsuario.NOME_TABELA;

}