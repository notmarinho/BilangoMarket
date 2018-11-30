package com.example.mateus.bilangomarket.CRUD;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Create extends SQLiteOpenHelper {

    private static final String NOME_DB = "BANCO";
    private static final int VERSAO_DB = 1;
    private static final String TABELA_USUARIO = "TABELA_USUARIO";

    private static final String PATH_DB = "/data/user/0/com.example.mateus.bilangomarket/database/BANCO";
    private Context mContext;
    private SQLiteDatabase db;



    public Create(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
        this.mContext = context;
        db = getWritableDatabase(); /*Agora  o banco de dados é do tipo Writable, para escrever o banco*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean createTable(){
        openDB();
        String createTable = "CREATE TABLE IF NOT EXISTS "+ TABELA_USUARIO +" ("
                +"NOME TEXT,"
                +"EMAIL TEXT,"
                +"SENHA TEXT,"
                +"ATIVO TEXT)";
        try{
            db.execSQL(createTable);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }


    private void openDB(){
        if(!db.isOpen()){
            db = mContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE, null);
        }
    }


}