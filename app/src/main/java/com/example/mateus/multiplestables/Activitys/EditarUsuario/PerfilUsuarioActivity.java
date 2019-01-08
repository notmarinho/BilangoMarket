package com.example.mateus.multiplestables.Activitys.EditarUsuario;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mateus.multiplestables.Activitys.EditarUsuario.EditarUsuarioMenuActivity;
import com.example.mateus.multiplestables.DATA.UsuarioDAO;
import com.example.mateus.multiplestables.Menu_deslizante;
import com.example.mateus.multiplestables.R;
import com.example.mateus.multiplestables.Usuario;

import java.util.ArrayList;

public class PerfilUsuarioActivity extends AppCompatActivity {

    public static Activity menuActivity;
    String usuario_email;
    TextView texto_NomeUsuario;
    Usuario usuario;
    ArrayList<Integer> idAnunciosCarrinho;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        menuActivity = this;
        texto_NomeUsuario = (TextView)findViewById(R.id.txt_NomeUsuario);
        Bundle bundle = getIntent().getExtras();

        if (bundle!= null){
            usuario_email = bundle.getString("usuario_email");
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            usuario = usuarioDAO.getUsuarioByEmail(usuario_email);
            idAnunciosCarrinho = bundle.getIntegerArrayList("carrinho");
            texto_NomeUsuario.setText(usuario.getNome());
        }
    }
    @Override
    public void onBackPressed(){
        Intent it = new Intent(this, Menu_deslizante.class);
        Bundle b = new Bundle();
        b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
        it.putExtra("usuario_email", usuario_email);
        it.putExtras(b);
        startActivity(it);
        finish();
    }


   public void act_editarUsuarioMenu(View view){
        Bundle b = new Bundle();
        b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
        Intent it = new Intent(this, EditarUsuarioMenuActivity.class);
        it.putExtra("usuario_email", usuario_email);
        it.putExtras(b);
        startActivity(it);
        finish();
    }

    public void act_menuDeslizante(View view){
        Intent it = new Intent(this, Menu_deslizante.class);
        Bundle b = new Bundle();
        b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
        it.putExtra("usuario_email", usuario_email);
        it.putExtras(b);
        startActivity(it);
        finish();
    }
}
