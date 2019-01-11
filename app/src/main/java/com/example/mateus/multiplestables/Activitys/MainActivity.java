package com.example.mateus.multiplestables.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mateus.multiplestables.Anuncio;
import com.example.mateus.multiplestables.DATA.AnunciosAdapter;
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
        final AnuncioDAO anuncioDAO = new AnuncioDAO(getApplicationContext());
        ArrayAdapter adapter = new AnunciosAdapter(this, anuncioDAO.getAllAnuncios());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Anuncio> lista = anuncioDAO.getAllAnuncios();
                String anuncio_nome      = lista.get(position).getNome();
                String anuncio_preco     = lista.get(position).getPrecoString();
                String anuncio_descricao = lista.get(position).getDescricao();
                int    anuncio_ID        = lista.get(position).getID();
                int    anunciante        = lista.get(position).getDonoID();
                Intent it = new Intent(getApplicationContext(), ItemClicadoActivity.class);
                it.putExtra("anuncio_nome", anuncio_nome);                            //Enviando os dados para a Activity que aparecera todos os dados do anuncio
                it.putExtra("anuncio_preco", anuncio_preco);
                it.putExtra("anuncio_descricao", anuncio_descricao);
                it.putExtra("anuncio_ID", anuncio_ID);
                it.putExtra("anunciante", anunciante);
                startActivity(it);
            }
        });

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
