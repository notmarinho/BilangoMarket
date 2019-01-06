package com.example.mateus.multiplestables.Activitys;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.mateus.multiplestables.R;

public class ItemClicadoActivity extends AppCompatActivity {

    String anuncio_nome;
    String anuncio_preco;
    String anuncio_descricao;

    TextView txt_anuncio_nome;
    TextView txt_anuncio_preco;
    TextView txt_anuncio_descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_clicado);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        txt_anuncio_nome  = (TextView)findViewById(R.id.telaAnuncio_nome);
        txt_anuncio_preco = (TextView)findViewById(R.id.telaAnuncio_preco);
        txt_anuncio_descricao = (TextView)findViewById(R.id.telaAnuncio_descricao);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
                                                                                //Recebendo as strings que foram mandadas da Activity anterior
            anuncio_nome      = bundle.getString("anuncio_nome");
            anuncio_preco     = bundle.getString("anuncio_preco");
            anuncio_descricao = bundle.getString("anuncio_descricao");
                                                                                //Mudando os textos para as informaçoes do anuncio que foi clicado
            txt_anuncio_nome.setText(anuncio_nome);
            txt_anuncio_preco.setText(anuncio_preco);
            txt_anuncio_descricao.setText(anuncio_descricao);
        }

        FloatingActionButton fab = findViewById(R.id.floatingButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Você precisa estar logado para adicionar este item ao carrinho", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
