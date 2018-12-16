package com.example.mateus.bilangomarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MenuUsuario extends AppCompatActivity {
    TextView text_usuarioNome;
    String usuario_nome;
    String usuario_email;
    String usuario_senha;
    long usuario_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);
        text_usuarioNome = (TextView)findViewById(R.id.txt_nomeUsuario);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            usuario_nome = bundle.getString("usuario_nome");
            usuario_email = bundle.getString("usuario_email");
            usuario_senha = bundle.getString("usuario_senha");
            usuario_ID = bundle.getLong("usuario_ID");
            text_usuarioNome.setText(usuario_nome);
        }

    }

public void act_listaUsuarios(View v){
    Intent it = new Intent(this, ListaUsuarios.class);
    startActivity(it);
}

public void act_deslogar(View v){
        LoginActivity loginActivity = new LoginActivity();
        loginActivity.setUsuarioLogado(null);
        finish();
    }
public void act_cadastrarAnuncio(View v){
        Intent it = new Intent(this, CadastrarAnuncio.class);
        startActivity(it);
}

/*public void act_deletarUsuario(View v){
        Intent it = new Intent(this, DeletarUsuario.class);
        it.putExtra("usuario_nome", usuario_nome);
        it.putExtra("usuario_email", usuario_email);
        it.putExtra("usuario_senha", usuario_senha);
        it.putExtra("usuario_ID", usuario_ID);
        startActivity(it);

        EM COMENTARIO PORQUE A ACT DE DELETAR USUARIO SUMIU, E EU NAO SEI COMO
}*/

public void act_menuEditarUsuario(View view){
        Intent it = new Intent(this, MenuEditarUsuario.class);
        it.putExtra("usuario_nome", usuario_nome);
        it.putExtra("usuario_email", usuario_email);
        it.putExtra("usuario_senha", usuario_senha);
        it.putExtra("usuario_ID", usuario_ID);
        startActivity(it);
}
}
