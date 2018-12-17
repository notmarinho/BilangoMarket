package com.example.mateus.bilangomarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mateus.bilangomarket.DATA.UsuarioDAO;

public class DesativarUsuario extends AppCompatActivity {

    String usuario_email;
    String usuario_senha;
    EditText edt_email;
    EditText edt_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desativar_usuario);
        edt_email = (EditText)findViewById(R.id.edt_desativar_email);
        edt_senha = (EditText)findViewById(R.id.edt_desativar_senha);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            usuario_email = bundle.getString("usuario_email");
            usuario_senha = bundle.getString("usuario_senha");
        }
    }

    public void desativarUsuario(View view){
        String email = edt_email.getText().toString();
        String senha = edt_senha.getText().toString();
        if (email.equals(usuario_email) && senha.equals(usuario_senha)){
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            usuarioDAO.desativarUsuario(usuario_email);
            Toast.makeText(this, "Usuario desativado com sucesso", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
            finish();
        }else Toast.makeText(this, "O email ou senha nao corresponde ao do usuario", Toast.LENGTH_SHORT).show();
    }

    public void fecharAct(View view){
        Intent it = new Intent(this, MenuEditarUsuario.class);
        startActivity(it);
        finish();
    }
}
