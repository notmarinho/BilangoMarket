package com.example.mateus.bilangomarket.Acitivitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.mateus.bilangomarket.Anuncio;
import com.example.mateus.bilangomarket.DATA.UsuarioDAO;
import com.example.mateus.bilangomarket.R;
import com.example.mateus.bilangomarket.Usuario;

public class CadastrarAnuncio extends AppCompatActivity {

    String usuario_email;
    EditText edt_nomeAnuncio;
    EditText edt_precoAnuncio;
    EditText edt_descricaoAnuncio;
    Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_anuncio);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            usuario_email = bundle.getString("usuario_email");
        }
        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        usuario = usuarioDAO.getUsuarioEmail(usuario_email);
        edt_nomeAnuncio = (EditText)findViewById(R.id.edt_nomeAnuncio);
        edt_precoAnuncio= (EditText)findViewById(R.id.edt_precoAnuncio);
        edt_descricaoAnuncio = (EditText)findViewById(R.id.edt_descricaoAnuncio);
    }


    public void fechar_act(View view){
        finish();
    }
}
