package com.example.mateus.multiplestables.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mateus.multiplestables.Anuncio;
import com.example.mateus.multiplestables.DATA.AnuncioDAO;
import com.example.mateus.multiplestables.R;

import java.util.ArrayList;

public class TelaDeAnunciosActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_anuncios);

        listView = (ListView)findViewById(R.id.listViewAnuncios);
        ArrayList<ArrayList> lista = new ArrayList();
        AnuncioDAO anuncioDAO = new AnuncioDAO(getApplicationContext());
        lista =  anuncioDAO.getAllAnunciosList();


    }



}
