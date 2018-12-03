package com.example.mateus.bilangomarket.CRUD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mateus.bilangomarket.Usuario;

import java.util.ArrayList;

public class Read extends SQLiteOpenHelper {

    private static final String NOME_DB = "BANCO";
    private static final int VERSAO_DB = 1;
    private static final String TABELA_USUARIO = "TABELA_USUARIO";

    private static final String PATH_DB = "/data/user/0/com.example.mateus.bilangomarket/database/BANCO";
    private Context mContext;
    private SQLiteDatabase db;



    public Read(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
        this.mContext = context;
        db = getReadableDatabase();     /*Agora  o banco de dados é do tipo Readable, para ler o banco*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Usuario> getUsuario(){
        openDB();
        ArrayList<Usuario> uArray = new ArrayList<>();
        String getUsuario = "SELECT * FROM " + TABELA_USUARIO;

        try{
            Cursor c = db.rawQuery(getUsuario, null);

            if(c.moveToFirst()){
                do{
                    /*Definindo os atributos do usuario a ser exibido de acordo com a posição da coluna no BD*/
                    Usuario u = new Usuario();
                    u.setNome(c.getString(0));
                    u.setEmail(c.getString(1));
                    u.setSenha(c.getString(2));
                    u.setAtivo(c.getString(3));
                    uArray.add(u);
                }while(c.moveToNext());
                c.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            db.close();
        }
        return uArray;
    }





    private void openDB(){
        if(!db.isOpen()){
            db = mContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE, null);

        }

    }


}