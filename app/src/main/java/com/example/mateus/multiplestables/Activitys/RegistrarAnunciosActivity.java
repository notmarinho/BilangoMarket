package com.example.mateus.multiplestables.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mateus.multiplestables.Anuncio;
import com.example.mateus.multiplestables.DATA.AnuncioDAO;
import com.example.mateus.multiplestables.DATA.UsuarioDAO;
import com.example.mateus.multiplestables.R;
import com.example.mateus.multiplestables.Usuario;

import java.util.ArrayList;

public class RegistrarAnunciosActivity extends AppCompatActivity {

    String usuario_email;
    Usuario usuario;
    EditText edt_anuncio_nome;
    EditText edt_anuncio_preco;
    EditText edt_anuncio_descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_anuncios);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            usuario_email = bundle.getString("usuario_email");
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            usuario = usuarioDAO.getUsuarioByEmail(usuario_email);
        }

        edt_anuncio_nome  = (EditText)findViewById(R.id.edt_anuncioNome);
        edt_anuncio_preco = (EditText)findViewById(R.id.edt_anuncio_preco);
        edt_anuncio_descricao = (EditText)findViewById(R.id.edt_anuncioDescricao);
    }

    public void cadastrarAnuncio(View view){
        String nome = edt_anuncio_nome.getText().toString();
        float preco = Float.valueOf(edt_anuncio_preco.getText().toString());
        String descricao = edt_anuncio_descricao.getText().toString();
        int DonoID = usuario.getID();

        Anuncio anuncio = new Anuncio();
        anuncio.setNome(nome);
        anuncio.setPreço(preco);
        anuncio.setDescricao(descricao);
        anuncio.setDonoID(DonoID);


        AnuncioDAO anuncioDAO = new AnuncioDAO(getApplicationContext());
        anuncioDAO.inserirAnuncio(anuncio);
        ArrayList<Anuncio> listaAnuncios = anuncioDAO.getAllAnuncios();
        Toast.makeText(this, "Anuncio Cadastrado", Toast.LENGTH_SHORT).show();
        finish();
    }

}
