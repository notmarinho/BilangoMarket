package com.example.mateus.multiplestables.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mateus.multiplestables.Anuncio;
import com.example.mateus.multiplestables.DATA.AnuncioDAO;
import com.example.mateus.multiplestables.DATA.UsuarioDAO;
import com.example.mateus.multiplestables.Menu_deslizante;
import com.example.mateus.multiplestables.R;
import com.example.mateus.multiplestables.Usuario;

import java.util.ArrayList;

public class RegistrarAnunciosActivity extends AppCompatActivity {

    String usuario_email;
    Usuario usuario;
    EditText edt_anuncio_nome;
    EditText edt_anuncio_preco;
    EditText edt_anuncio_descricao;
    ArrayList<Integer> idAnunciosCarrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_anuncios);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            usuario_email      = bundle.getString("usuario_email");
            idAnunciosCarrinho = bundle.getIntegerArrayList("carrinho");
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            usuario = usuarioDAO.getUsuarioByEmail(usuario_email);

        }

        edt_anuncio_nome  = (EditText)findViewById(R.id.edt_anuncioNome);
        edt_anuncio_preco = (EditText)findViewById(R.id.edt_anuncio_preco);
        edt_anuncio_descricao = (EditText)findViewById(R.id.edt_anuncioDescricao);
    }

    public void cadastrarAnuncio(View view){
        boolean erro = false;
        String nome      = edt_anuncio_nome.getText().toString();
        String preco     = edt_anuncio_preco.getText().toString();
        String descricao = edt_anuncio_descricao.getText().toString();
        int DonoID       = usuario.getID();


        if (erro = isCampoVazio(nome)){
            Toast.makeText(this, "Informe um nome para o anuncio", Toast.LENGTH_SHORT).show();
            edt_anuncio_nome.requestFocus();
        }
        else
        if (erro = isCampoVazio(preco)){
            Toast.makeText(this, "Informe um preço para o anuncio", Toast.LENGTH_SHORT).show();
            edt_anuncio_preco.requestFocus();
        }
        else
        if (erro = isCampoVazio(descricao)){
            Toast.makeText(this, "Informe uma descrição para o anuncio", Toast.LENGTH_SHORT).show();
            edt_anuncio_descricao.requestFocus();
        }

        if (!erro){
                                                                                //Caso nao houver nenhum erro, o anuncio é cadastrado no banco de dados
            Anuncio anuncio = new Anuncio();
            anuncio.setNome(nome);
            anuncio.setPreço(Float.valueOf(preco));
            anuncio.setDescricao(descricao);
            anuncio.setDonoID(DonoID);

            AnuncioDAO anuncioDAO = new AnuncioDAO(getApplicationContext());
            anuncioDAO.inserirAnuncio(anuncio);                                 //Linha onde o anuncio é inserido no banco de dados
            ArrayList<Anuncio> listaAnuncios = anuncioDAO.getAllAnuncios();
            Toast.makeText(this, "Anuncio Cadastrado", Toast.LENGTH_SHORT).show();

            Bundle b = new Bundle();
            b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
            Intent it = new Intent(this, Menu_deslizante.class); //Chamando a act novamente para o Listview ser atualizado com o novo item que foi inserido
            it.putExtra("usuario_email", usuario_email);
            it.putExtras(b);
            startActivity(it);
            Menu_deslizante.menu_deslizante.finish();
            finish();
        }
    }


    private boolean isCampoVazio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());  //Verificando se o campo esta vazio
        return resultado;
    }
}
