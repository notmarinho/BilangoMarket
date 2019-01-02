package com.example.mateus.multiplestables.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mateus.multiplestables.R;

public class EditarUsuarioMenuActivity extends AppCompatActivity {

    public static Activity editarUsuarioActivity;
    String usuario_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario_menu);
        editarUsuarioActivity = this;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            usuario_email = bundle.getString("usuario_email");
        }
    }

    public void act_menuPrincipal(View view){
        Intent it = new Intent(this, MenuActivity.class);
        it.putExtra("usuario_email", usuario_email);
        startActivity(it);
        finish();
    }

    public void act_editarNome(View view){
        Intent it = new Intent(this, EditarNomeActivity.class);
        it.putExtra("usuario_email", usuario_email);
        startActivity(it);
        finish();
    }

    public void act_editarSenha(View view){
        Intent it = new Intent(this, EditarSenhaActivity.class);
        it.putExtra("usuario_email", usuario_email);
        startActivity(it);

    }

    public void act_desativarUsuario(View view){
        Intent it = new Intent(this, EditarDesativarContaActivity.class);
        it.putExtra("usuario_email", usuario_email);
        startActivity(it);

    }
}
