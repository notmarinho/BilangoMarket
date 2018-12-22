package com.example.mateus.bilangomarket.Acitivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mateus.bilangomarket.DATA.UsuarioDAO;
import com.example.mateus.bilangomarket.R;
import com.example.mateus.bilangomarket.Usuario;

import java.util.ArrayList;

public class ListaUsuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        ListView listView = (ListView)findViewById(R.id.listaUsuarios);

        ArrayList<String> theList = new ArrayList<>();
        UsuarioDAO r = new UsuarioDAO(getApplicationContext());
        ArrayList<Usuario> uArray = r.getTodosUsuarios();

        if(uArray.size() == 0){
            Toast.makeText(this, "O banco de dados está vazio", Toast.LENGTH_SHORT).show();
        }else{
            for (int i = 0; i < uArray.size() ; i++) {
                Usuario u = uArray.get(i);
                if (u.getAtivo() == 1){
                    theList.add(u.getNome());
                }
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
            listView.setAdapter(arrayAdapter);
        }
    }

    public void fecharAct(View v){
        finish();
    }
}
