package com.example.mateus.multiplestables.DATA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mateus.multiplestables.Anuncio;

import java.util.ArrayList;

public class AnuncioDAO {
    public static final String TAG = "AnuncioDAO";

    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;
    private Context mContext;

    public AnuncioDAO(Context context){
        this.mContext = context;
        mDBHelper = new DBHelper(context);
        //Abrindo o banco
        try {
            open();
        }catch (SQLException e){
            Log.e(TAG, "SQLExecption on openning database " + e.getStackTrace());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException{
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void close(){
        mDBHelper.close();
    }

    public void inserirAnuncio(Anuncio anuncio){
        ContentValues valores = new ContentValues();
        valores.put(DBHelper.COLUNA_ANUNCIO_NOME, anuncio.getNome());
        valores.put(DBHelper.COLUNA_ANUNCIO_PRECO, anuncio.getPreço());
        valores.put(DBHelper.COLUNA_ANUNCIO_DESCRICAO, anuncio.getDescricao());
        valores.put(DBHelper.COLUNA_ANUNCIO_DONOID, anuncio.getDonoID());

        long LinhaID = mDatabase.insert(DBHelper.TABELA_ANUNCIO, null, valores);

        anuncio.setID((int) LinhaID);
        close();
    }

    public boolean deleteAnuncio(int ID) {
        DBHelper dbHelper = new DBHelper(mContext);
        open();

        String deleteUsuario = dbHelper.COLUNA_ANUNCIO_ID + " = '" + ID + "'";

        try {
            mDatabase.delete(dbHelper.TABELA_ANUNCIO, deleteUsuario, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }

    }

    public ArrayList<Anuncio> getAllAnuncios(){
        DBHelper dbHelper = new DBHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<Anuncio> listaAnuncios = new ArrayList<>();
        String getAnuncios = "SELECT * FROM " + DBHelper.TABELA_ANUNCIO;

        try{
            Cursor cursor = db.rawQuery(getAnuncios, null);

            if (cursor.moveToFirst()){
                do {
                    Anuncio anuncio = new Anuncio();
                    anuncio.setID(cursor.getInt(0));
                    anuncio.setNome(cursor.getString(1));
                    anuncio.setPreço(cursor.getFloat(2));
                    anuncio.setDescricao(cursor.getString(3));
                    anuncio.setDonoID(cursor.getInt(4));
                    listaAnuncios.add(anuncio);
                }while (cursor.moveToNext());
                cursor.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            db.close();
        }

        return listaAnuncios;
    }

    public ArrayList<ArrayList> getAllAnunciosList(){
        ArrayList<Anuncio> listaAnuncio = getAllAnuncios();
        ArrayList<ArrayList> listaFinal = new ArrayList<>();

        for (int i = 0; i < listaAnuncio.size(); i++){
            ArrayList<String> item = new ArrayList<>();
            Anuncio anuncio = listaAnuncio.get(i);
            item.add(anuncio.getNome());
            item.add(Float.toString(anuncio.getPreço()));
            item.add(anuncio.getDescricao());
            listaFinal.add(item);
        }

        return listaFinal;
    }

    public Anuncio getAnuncioByID(int ID){
        Anuncio pesquisarAnuncio = null;

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + DBHelper.TABELA_ANUNCIO + " WHERE _id = " + ID, null);

        if (cursor.getCount() != 0){
            pesquisarAnuncio = criarAnuncio(cursor);
        }

        cursor.close();
        close();

        return pesquisarAnuncio;
    }

    public Anuncio criarAnuncio(Cursor cursor){

        cursor.moveToNext();

        int idIndex     = cursor.getColumnIndexOrThrow(DBHelper.COLUNA_ANUNCIO_ID);
        int nomeIndex   = cursor.getColumnIndexOrThrow(DBHelper.COLUNA_ANUNCIO_NOME);
        int precoIndex       = cursor.getColumnIndexOrThrow(DBHelper.COLUNA_ANUNCIO_PRECO);
        int descricaoIndex   = cursor.getColumnIndexOrThrow(DBHelper.COLUNA_ANUNCIO_DESCRICAO);
        int donoIDIndex      = cursor.getColumnIndexOrThrow(DBHelper.COLUNA_ANUNCIO_DONOID);

        int id       = cursor.getInt(idIndex);
        int donoID   = cursor.getInt(donoIDIndex);
        float  preco = cursor.getFloat(precoIndex);
        String descricao = cursor.getString(descricaoIndex);
        String nome  = cursor.getString(nomeIndex);

        Anuncio anuncio = new Anuncio();
        anuncio.setID(id);
        anuncio.setNome(nome);
        anuncio.setPreço(preco);
        anuncio.setDescricao(descricao);
        anuncio.setDonoID(donoID);

        return anuncio;
    }
}
