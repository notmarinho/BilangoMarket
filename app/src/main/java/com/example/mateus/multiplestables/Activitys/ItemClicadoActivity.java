package com.example.mateus.multiplestables.Activitys;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mateus.multiplestables.Anuncio;
import com.example.mateus.multiplestables.Menu_deslizante;
import com.example.mateus.multiplestables.R;

import java.util.ArrayList;

public class ItemClicadoActivity extends AppCompatActivity {

    String anuncio_nome;
    String anuncio_preco;
    String anuncio_descricao;
    int    anuncio_ID;

    TextView txt_anuncio_nome;
    TextView txt_anuncio_preco;
    TextView txt_anuncio_descricao;

    String usuario_email;

    ArrayList<Integer> idAnunciosCarrinho;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_clicado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        txt_anuncio_nome      = (TextView) findViewById (R.id.telaAnuncio_nome);
        txt_anuncio_preco     = (TextView) findViewById (R.id.telaAnuncio_preco);
        txt_anuncio_descricao = (TextView) findViewById (R.id.telaAnuncio_descricao);



        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //Recebendo as strings que foram mandadas da Activity anterior
            anuncio_nome      = bundle.getString("anuncio_nome");
            anuncio_preco     = bundle.getString("anuncio_preco");
            anuncio_descricao = bundle.getString("anuncio_descricao");
            anuncio_ID        = bundle.getInt   ("anuncio_ID");

            idAnunciosCarrinho= bundle.getIntegerArrayList("carrinho");

            //Mudando os textos para as informaçoes do anuncio que foi clicado
            txt_anuncio_nome.setText(anuncio_nome);
            txt_anuncio_preco.setText(anuncio_preco);
            txt_anuncio_descricao.setText(anuncio_descricao);

            usuario_email = bundle.getString("usuario_email");
        }

        FloatingActionButton fab = findViewById(R.id.floatingButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usuario_email == null) {
                    Snackbar.make(view, "Você precisa estar logado para adicionar este item ao carrinho", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    if (!idAnunciosCarrinho.contains(anuncio_ID)) {
                        idAnunciosCarrinho.add(anuncio_ID);
                        Toast.makeText(ItemClicadoActivity.this, anuncio_nome + " adicionado ao carrinho", Toast.LENGTH_LONG).show();
                        Bundle b  = new Bundle();
                        b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
                        Intent it = new Intent(getApplicationContext(), Menu_deslizante.class);
                        it.putExtras(b);
                        it.putExtra("usuario_email", usuario_email);
                        Menu_deslizante.menu_deslizante.finish();
                        finish();
                        startActivity(it);
                    }else Toast.makeText(ItemClicadoActivity.this, "Este anuncio ja esta no carrinho", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}