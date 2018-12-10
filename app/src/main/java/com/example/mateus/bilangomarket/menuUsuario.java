package com.example.mateus.bilangomarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class menuUsuario extends AppCompatActivity {
    TextView nome_usuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);
        nome_usuario = (TextView)findViewById(R.id.txt_nomeUsuario);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String usuario_nome = bundle.getString("usuario_nome");
            String usuario_email = bundle.getString("usuario_email");
            nome_usuario.setText(usuario_nome);
        }

    }

public void act_listaUsuarios(View v){
    Intent it = new Intent(this, ListaUsuarios.class);
    startActivity(it);
}

public void act_deslogar(View v){
        LoginActivity loginActivity = new LoginActivity();
        loginActivity.setUsuarioLogado(null);
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
public void act_cadastrarAnuncio(View v){
        Intent it = new Intent(this, CadastrarAnuncio.class);
        startActivity(it);
}
}
