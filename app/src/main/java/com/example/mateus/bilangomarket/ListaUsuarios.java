package com.example.mateus.bilangomarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mateus.bilangomarket.CRUD.Read;

import java.util.ArrayList;

public class ListaUsuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        ListView listView = (ListView)findViewById(R.id.listaUsuarios);

        ArrayList<String> theList = new ArrayList<>();
        Read r = new Read(getApplicationContext());
        ArrayList<Usuario> uArray = r.getUsuario();

        if(uArray.size() < 1){
            Toast.makeText(this, "O banco de dados está vazio", Toast.LENGTH_SHORT).show();
        }else{
            for (int i = 0; i < uArray.size() ; i++) {
                Usuario u = uArray.get(i);
                theList.add(u.getNome());
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
            listView.setAdapter(arrayAdapter);
        }
    }

    public void fecharAct(View v){
        finish();
    }
}
