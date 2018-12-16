package com.example.mateus.bilangomarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuEditarUsuario extends AppCompatActivity {
    String usuario_nome;
    String usuario_email;
    String usuario_senha;
    long usuario_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            usuario_nome = bundle.getString("usuario_nome");
            usuario_email = bundle.getString("usuario_email");
            usuario_senha = bundle.getString("usuario_senha");
            usuario_ID = bundle.getLong("usuario_ID");
        }
    }

    public void act_editarSenha(View view){
        Intent it = new Intent(this, EditarSenha.class);
        it.putExtra("usuario_senha", usuario_senha);
        it.putExtra("usuario_email", usuario_email);
        startActivity(it);
    }

    public void act_editarNomeUsuario(View view){
        Intent it = new Intent(this, EditarNomeUsuario.class);
        it.putExtra("usuario_email", usuario_email);
        startActivity(it);
    }
}
