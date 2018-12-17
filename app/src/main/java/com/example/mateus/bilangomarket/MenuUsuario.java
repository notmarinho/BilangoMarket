package com.example.mateus.bilangomarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mateus.bilangomarket.DATA.UsuarioDAO;


public class MenuUsuario extends AppCompatActivity {
    TextView text_usuarioNome;
    String usuario_email;
    Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);
        text_usuarioNome = (TextView)findViewById(R.id.txt_nomeUsuario);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            usuario_email = bundle.getString("usuario_email");
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        usuario = usuarioDAO.getUsuarioEmail(usuario_email);
        text_usuarioNome.setText(usuario.getNome());
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

public void act_menuEditarUsuario(View view){
        Intent it = new Intent(this, MenuEditarUsuario.class);
        it.putExtra("usuario_nome",  usuario.getNome());
        it.putExtra("usuario_email", usuario.getEmail());
        it.putExtra("usuario_senha", usuario.getSenha());
        it.putExtra("usuario_ID",    usuario.getID());
        startActivity(it);
}
}
