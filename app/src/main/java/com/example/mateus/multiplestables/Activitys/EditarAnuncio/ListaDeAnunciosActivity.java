package com.example.mateus.multiplestables.Activitys.EditarAnuncio;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mateus.multiplestables.Anuncio;
import com.example.mateus.multiplestables.DATA.AnuncioDAO;
import com.example.mateus.multiplestables.DATA.AnunciosAdapter;
import com.example.mateus.multiplestables.DATA.UsuarioDAO;
import com.example.mateus.multiplestables.R;
import com.example.mateus.multiplestables.Usuario;

import java.util.ArrayList;

public class ListaDeAnunciosActivity extends AppCompatActivity {
    String usuario_email;
    Usuario usuario;
    ArrayList<Integer> idAnunciosCarrinho;
    public static Activity ListaDeAnunciosActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_anuncios);
        ListaDeAnunciosActivity = this;
        ListView listView = findViewById(R.id.listView);
        Bundle bundle     = getIntent().getExtras();
        if (bundle != null){
            usuario_email = bundle.getString("usuario_email");
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            usuario = usuarioDAO.getUsuarioByEmail(usuario_email);
            idAnunciosCarrinho = bundle.getIntegerArrayList("carrinho");
        }

        ArrayList<Anuncio> listaAnunciosUsuario = new ArrayList<>();
        final AnuncioDAO anuncioDAO = new AnuncioDAO(getApplicationContext());
        ArrayList<Anuncio> listaAnuncios = anuncioDAO.getAllAnuncios();
        for (int i = 0; i < listaAnuncios.size(); i++){
            Anuncio anuncio = listaAnuncios.get(i);
            if (anuncio.getDonoID() == usuario.getID()){
                listaAnunciosUsuario.add(anuncio);
            }
        }
        ArrayAdapter adapter = new AnunciosAdapter(this, listaAnunciosUsuario);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Anuncio> lista = anuncioDAO.getAllAnuncios();
                String anuncio_nome      = lista.get(position).getNome();
                String anuncio_preco     = lista.get(position).getPrecoString();
                String anuncio_descricao = lista.get(position).getDescricao();
                int    anuncio_ID        = lista.get(position).getID();
                Bundle b  = new Bundle();
                b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
                Intent it = new Intent(getApplicationContext(), EditarAnuncioMenuActivity.class);
                it.putExtras(b);
                it.putExtra("anuncio_nome", anuncio_nome);                            // Enviando os dados para a Activity que aparecera todos os dados do anuncio
                it.putExtra("anuncio_preco", anuncio_preco);
                it.putExtra("anuncio_descricao", anuncio_descricao);
                it.putExtra("anuncio_ID", anuncio_ID);
                it.putExtra("usuario_email", usuario_email);
                startActivity(it);
            }
        });
    }

    public void fechar_act(View view){
        finish();
    }
}
