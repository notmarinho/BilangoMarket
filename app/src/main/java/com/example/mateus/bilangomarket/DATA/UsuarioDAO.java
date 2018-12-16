package com.example.mateus.bilangomarket.DATA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.mateus.bilangomarket.Usuario;

import java.util.ArrayList;

public class UsuarioDAO {
    private Context contexto;
    public UsuarioDAO(Context context) {
        this.contexto = context;}

    public void inserirUsuario(Usuario usuario){
        AjudanteBD ajudante = new AjudanteBD(contexto);
        SQLiteDatabase db = ajudante.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(ContratoUsuario.COLUNA_NOME,   usuario.getNome());
        valores.put(ContratoUsuario.COLUNA_EMAIL,  usuario.getEmail());
        valores.put(ContratoUsuario.COLUNA_SENHA,  usuario.getSenha());
        valores.put(ContratoUsuario.COLUNA_STATUS,  usuario.getAtivo());
        db.insert(Contrato.DB_NAME,null, valores);

        long novaLinhaID = db.insert(ContratoUsuario.NOME_TABELA, null, valores);

        usuario.setID( novaLinhaID);
        db.close();
    }
    public Usuario getUsuarioEmail(String email){
        AjudanteBD ajudante = new AjudanteBD (contexto);
        SQLiteDatabase db = ajudante.getReadableDatabase();

        Usuario pesquisarUsuario = null;

        String[] projection = {BaseColumns._ID, ContratoUsuario.COLUNA_NOME,
                ContratoUsuario.COLUNA_EMAIL, ContratoUsuario.COLUNA_SENHA,
                ContratoUsuario.COLUNA_ANUNCIOS, ContratoUsuario.COLUNA_STATUS
                    };

        String selection = ContratoUsuario.COLUNA_EMAIL+ " = ?";
        String[] selectionArgs= {email.trim().toLowerCase()};

        Cursor cursor = db.query(ContratoUsuario.NOME_TABELA,
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
        db.close();
        return pesquisarUsuario;

    }

    public Usuario criarUsuario(Cursor cursor){

        cursor.moveToNext();

        int idIndex    = cursor.getColumnIndexOrThrow(ContratoUsuario._ID);
        int nomeIndex  = cursor.getColumnIndexOrThrow(ContratoUsuario.COLUNA_NOME);
        int emailIndex = cursor.getColumnIndexOrThrow(ContratoUsuario.COLUNA_EMAIL);
        int statusIndex = cursor.getColumnIndexOrThrow(ContratoUsuario.COLUNA_STATUS);
        int senhaIndex = cursor.getColumnIndexOrThrow(ContratoUsuario.COLUNA_SENHA);
        int anunciosIndex = cursor.getColumnIndexOrThrow(ContratoUsuario.COLUNA_ANUNCIOS);

        long id = cursor.getInt(idIndex);
        String nome = cursor.getString(nomeIndex);
        String email = cursor.getString(emailIndex);
        String senha = cursor.getString(senhaIndex);
        int status = cursor.getInt(statusIndex);
        long anuncios = cursor.getLong(anunciosIndex);

        Usuario usuario = new Usuario();
        usuario.setID(id);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setAtivo(status);

        return usuario;
    }

    public ArrayList<Usuario> getTodosUsuarios(){
        AjudanteBD ajudante = new AjudanteBD (contexto);
        SQLiteDatabase db = ajudante.getReadableDatabase();

        ArrayList<Usuario> usuariosLista = new ArrayList<>();
        String getUsuario = "SELECT * FROM " + ContratoUsuario.NOME_TABELA;

        try{
            Cursor c = db.rawQuery(getUsuario, null);

            if(c.moveToFirst()){
                do{
                    Usuario u = new Usuario();
                    u.setNome(c.getString(1));
                    u.setEmail(c.getString(2));
                    u.setSenha(c.getString(3));
                    u.setAtivo(Integer.parseInt(c.getString(5)));
                    usuariosLista.add(u);
                }while(c.moveToNext());
                c.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            db.close();
        }
        return usuariosLista;

    }
    public boolean desativarUsuario(Usuario u){
        AjudanteBD ajudante = new AjudanteBD (contexto);
        SQLiteDatabase db = ajudante.getReadableDatabase();

        try{
            String where = "EMAIL = '" + u.getEmail() + "'";  /*Parametro que sera buscado no banco nesse caso o EMAIL*/
            ContentValues cv = new ContentValues();
            cv.put(ContratoUsuario.COLUNA_STATUS,   0);      /*Definindo os valores de cada tabela*/
            db.update(ContratoUsuario.NOME_TABELA, cv, where, null);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean editarSenha(String email, String novaSenha){
        AjudanteBD ajudante = new AjudanteBD(contexto);
        SQLiteDatabase db = ajudante.getReadableDatabase();

        try {
            String where = "EMAIL = '" + email + "'";
            ContentValues cv = new ContentValues();
            cv.put(ContratoUsuario.COLUNA_SENHA, novaSenha);
            db.update(ContratoUsuario.NOME_TABELA, cv, where, null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean editarNomeUsuario(String email, String nomeUsuario){
        AjudanteBD ajudante = new AjudanteBD(contexto);
        SQLiteDatabase db = ajudante.getReadableDatabase();

        try {
            String where = "EMAIL = '" + email + "'";
            ContentValues cv = new ContentValues();
            cv.put(ContratoUsuario.COLUNA_NOME, nomeUsuario);
            db.update(ContratoUsuario.NOME_TABELA, cv, where, null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }


}
