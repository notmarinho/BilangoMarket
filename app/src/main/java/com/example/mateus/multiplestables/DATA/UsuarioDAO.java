package com.example.mateus.multiplestables.DATA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import com.example.mateus.multiplestables.DATA.DBHelper;
import com.example.mateus.multiplestables.Usuario;

import java.util.ArrayList;

public class UsuarioDAO {
    public static final String TAG = "UsuarioDAO";

    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;
    private Context mContext;

    public UsuarioDAO(Context context){
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

    public void inserirUsuario(Usuario usuario){

        ContentValues valores = new ContentValues();
        valores.put(DBHelper.COLUNA_USUARIO_NOME, usuario.getNome());
        valores.put(DBHelper.COLUNA_USUARIO_EMAIL, usuario.getEmail());
        valores.put(DBHelper.COLUNA_USUARIO_SENHA, usuario.getSenha());
        valores.put(DBHelper.COLUNA_USUARIO_STATUS, usuario.getStatus());

        long LinhaID = mDatabase.insert(DBHelper.TABELA_USUARIO, null, valores);

        usuario.setID((int) LinhaID);
        close();
    }

    public Usuario getUsuarioByEmail(String email){

        Usuario pesquisarUsuario = null;

        String[] projection = {BaseColumns._ID, DBHelper.COLUNA_USUARIO_NOME,
                DBHelper.COLUNA_USUARIO_EMAIL, DBHelper.COLUNA_USUARIO_SENHA,
                DBHelper.COLUNA_USUARIO_STATUS};

        String selection = DBHelper.COLUNA_USUARIO_EMAIL + " = ?";
        String[] selectionArgs = {email.trim().toLowerCase()};

        Cursor cursor = mDatabase.query(DBHelper.TABELA_USUARIO,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.getCount() != 0){
            pesquisarUsuario = criarUsuario(cursor);
        }

        cursor.close();
        close();

        return pesquisarUsuario;
    }

    public Usuario getUsuarioByID(int ID){
        Usuario pesquisarUsuario = null;

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + DBHelper.TABELA_USUARIO + " WHERE _id = " + ID, null);


        if (cursor.getCount() != 0){
            pesquisarUsuario = criarUsuario(cursor);
        }

        cursor.close();
        close();

        return pesquisarUsuario;
    }

    public Usuario criarUsuario(Cursor cursor){

        cursor.moveToNext();

        int idIndex     = cursor.getColumnIndexOrThrow(DBHelper.COLUNA_USUARIO_ID);
        int nomeIndex   = cursor.getColumnIndexOrThrow(DBHelper.COLUNA_USUARIO_NOME);
        int emailIndex  = cursor.getColumnIndexOrThrow(DBHelper.COLUNA_USUARIO_SENHA);
        int senhaIndex  = cursor.getColumnIndexOrThrow(DBHelper.COLUNA_USUARIO_SENHA);
        int statusIndex = cursor.getColumnIndexOrThrow(DBHelper.COLUNA_USUARIO_STATUS);

        int id      = cursor.getInt(idIndex);
        String nome  = cursor.getString(nomeIndex);
        String email = cursor.getString(emailIndex);
        String senha = cursor.getString(senhaIndex);
        int status   = cursor.getInt(statusIndex);

        Usuario usuario = new Usuario();
        usuario.setID(id);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setStatus(status);

        return usuario;
    }

    public ArrayList<Usuario> getAllUsuarios(){
        DBHelper dbHelper = new DBHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        String getUsuarios = "SELECT * FROM " + DBHelper.TABELA_USUARIO;

        try{
            Cursor cursor = db.rawQuery(getUsuarios, null);

            if (cursor.moveToFirst()){
                do {
                    Usuario usuario = new Usuario();
                    usuario.setID(cursor.getInt(0));
                    usuario.setNome(cursor.getString(1));
                    usuario.setEmail(cursor.getString(2));
                    usuario.setSenha(cursor.getString(3));
                    usuario.setStatus(cursor.getInt(4));
                    listaUsuarios.add(usuario);
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

        return listaUsuarios;
    }

    public boolean desativarUsuario(String email){
        try {
            String where = "EMAIL = '" + email + "'";
            ContentValues cv = new ContentValues();
            cv.put(DBHelper.COLUNA_USUARIO_STATUS, 0);
            mDatabase.update(DBHelper.TABELA_USUARIO, cv, where, null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            close();
        }

    }

    public boolean ativarUsuario(String email){
        try {
            String where = "EMAIL = '" + email + "'";
            ContentValues cv = new ContentValues();
            cv.put(DBHelper.COLUNA_USUARIO_STATUS, 1);
            mDatabase.update(DBHelper.TABELA_USUARIO, cv, where, null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            close();
        }

    }

    public boolean editarSenha(String email, String novaSenha){
        try {
            String where = "EMAIL = '" + email + "'";
            ContentValues cv = new ContentValues();
            cv.put(DBHelper.COLUNA_USUARIO_SENHA, novaSenha);
            mDatabase.update(DBHelper.TABELA_USUARIO, cv, where, null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            close();
        }
    }

    public boolean editarNomeUsuario(String email, String nomeUsuario){
        try {
            String where = "EMAIL = '" + email + "'";
            ContentValues cv = new ContentValues();
            cv.put(DBHelper.COLUNA_USUARIO_NOME, nomeUsuario);
            mDatabase.update(DBHelper.TABELA_USUARIO, cv, where, null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            close();
        }
    }
}
