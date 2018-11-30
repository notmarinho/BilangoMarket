package com.example.mateus.bilangomarket.CRUD;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import com.example.mateus.bilangomarket.Usuario;

public class Delete extends SQLiteOpenHelper {

    private static final String NOME_DB = "BANCO";
    private static final int VERSAO_DB = 1;
    private static final String TABELA_USUARIO = "TABELA_USUARIO";

    private static final String PATH_DB = "/data/user/0/com.example.mateus.bilangomarket/database/BANCO";
    private Context mContext;
    private SQLiteDatabase db;


    public Delete(Context context) {
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

    public boolean deleteTable() {
        openDB();

        String deleteTable = "DROP TABLE IF EXISTS " + TABELA_USUARIO;
        try {
            db.execSQL(deleteTable);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    public boolean desativarUsuario(Usuario u){
        openDB();
        try{
            String where = "EMAIL = '" + u.getEmail() + "'";  /*Parametro que sera buscado no banco nesse caso o EMAIL*/
            ContentValues cv = new ContentValues();
            cv.put("ATIVO",   "0");      /*Definindo os valores de cada tabela*/
            db.update(TABELA_USUARIO, cv, where, null);
            return true;


        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean deleteUsuario(Usuario u) {
        openDB();

        String deleteUsuario = "EMAIL = '" + u.getEmail() + "'";

        try {
            db.delete(TABELA_USUARIO, deleteUsuario, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }

    }


    private void openDB() {
        if (!db.isOpen()) {
            db = mContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE, null);
        }
    }
}