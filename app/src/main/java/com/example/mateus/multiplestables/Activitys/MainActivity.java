package com.example.mateus.multiplestables.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mateus.multiplestables.Activitys.RegistrarUsuarioActivity;
import com.example.mateus.multiplestables.Anuncio;
import com.example.mateus.multiplestables.AnunciosAdapter;
import com.example.mateus.multiplestables.DATA.AnuncioDAO;
import com.example.mateus.multiplestables.DATA.UsuarioDAO;
import com.example.mateus.multiplestables.R;
import com.example.mateus.multiplestables.Usuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Activity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        ListView listView = (ListView)findViewById(R.id.listViewMain);
        AnuncioDAO anuncioDAO = new AnuncioDAO(getApplicationContext());
        ArrayAdapter adapter = new AnunciosAdapter(this, anuncioDAO.getAllAnuncios());
        listView.setAdapter(adapter);
    }




    public void act_cadastrarUsuario(View view){
        Intent it = new Intent(this, RegistrarUsuarioActivity.class);
        startActivity(it);
    }

    public void act_logar(View view){
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
    }
}
