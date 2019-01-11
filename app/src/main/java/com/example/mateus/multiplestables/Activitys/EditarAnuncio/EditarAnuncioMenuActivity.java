package com.example.mateus.multiplestables.Activitys.EditarAnuncio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mateus.multiplestables.DATA.AnuncioDAO;
import com.example.mateus.multiplestables.Menu_deslizante;
import com.example.mateus.multiplestables.R;

import java.util.ArrayList;

public class EditarAnuncioMenuActivity extends AppCompatActivity {

    String usuario_email;

    TextView txt_anuncioNome_atual;
    TextView txt_anuncioPreco_atual;
    TextView txt_anuncioDescricao_atual;

    ArrayList<Integer> idAnunciosCarrinho;

    EditText edt_anuncioNome_novo;
    EditText edt_anuncioPreco_novo;
    EditText edt_anuncioDescricao_novo;
    int      anuncio_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_editar_anuncio);

        txt_anuncioNome_atual      = findViewById(R.id.txt_anuncioNome_atual);
        txt_anuncioPreco_atual     = findViewById(R.id.txt_anuncioPreco_atual);
        txt_anuncioDescricao_atual = findViewById(R.id.txt_anuncioDescricao_atual);
        edt_anuncioNome_novo       = findViewById(R.id.edt_anuncioNome_novo);
        edt_anuncioPreco_novo      = findViewById(R.id.edt_anuncioPreco_novo);
        edt_anuncioDescricao_novo  = findViewById(R.id.edt_anuncioDescricao_novo);

        Bundle bundle = getIntent().getExtras();
        System.out.println("asd");
        if (bundle != null) {
            //Recebendo as strings que foram mandadas da Activity anterior
            String anuncio_nome      = bundle.getString("anuncio_nome");
            String anuncio_preco     = bundle.getString("anuncio_preco");
            String anuncio_descricao = bundle.getString("anuncio_descricao");
            anuncio_ID       = bundle.getInt   ("anuncio_ID");

            idAnunciosCarrinho = bundle.getIntegerArrayList("carrinho");

            //Mudando os textos para as informaçoes do anuncio que foi clicado

            txt_anuncioNome_atual.setText(anuncio_nome);
            txt_anuncioPreco_atual.setText(anuncio_preco+"0");
            txt_anuncioDescricao_atual.setText(anuncio_descricao);

            usuario_email = bundle.getString("usuario_email");
        }
    }

    public void editar(View view){
        String nome      = edt_anuncioNome_novo.getText().toString();
        String preco     = edt_anuncioPreco_novo.getText().toString();
        String descricao = edt_anuncioDescricao_novo.getText().toString();
        AnuncioDAO anuncioDAO = new AnuncioDAO(getApplicationContext());

        if (!isCampoVazio(nome)){
            anuncioDAO.editarNome(anuncio_ID, nome);
        }
        if (!isCampoVazio(preco)){
            anuncioDAO.editarPreco(anuncio_ID, Float.valueOf(preco));
        }
        if (!isCampoVazio(descricao)){
            anuncioDAO.editarDescricao(anuncio_ID, descricao);
        }

        Bundle b = new Bundle();
        b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
        Intent it = new Intent(this, ListaDeAnunciosActivity.class);
        it.putExtras(b);
        it.putExtra("usuario_email", usuario_email);
        ListaDeAnunciosActivity.ListaDeAnunciosActivity.finish();
        finish();
        startActivity(it);
    }

    private boolean isCampoVazio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());  /* Verificando se o campo esta vazio*/
        return resultado;
    }
}
