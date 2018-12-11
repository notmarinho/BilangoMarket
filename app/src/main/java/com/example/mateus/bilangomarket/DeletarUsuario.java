package com.example.mateus.bilangomarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mateus.bilangomarket.DATA.UsuarioDAO;
import com.example.mateus.bilangomarket.Usuario;
public class DeletarUsuario extends AppCompatActivity {
    String   usuario_email;
    String   usuario_senha;
    EditText edt_email;
    EditText edt_senha;
    TextView txt_avisoComNomeDoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_usuario);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            usuario_email = bundle.getString("usuario_email");
            usuario_senha = bundle.getString("usuario_senha");
        }

        edt_email = (EditText) findViewById(R.id.edt_emailDeletar);
        edt_senha = (EditText) findViewById(R.id.edt_senhaDeletar);
    }

    public void deletarUsuario(View view) {
        String email = edt_email.getText().toString();
        String senha = edt_senha.getText().toString();

        if (email.equals(usuario_email) && senha.equals(usuario_senha)) {
            Usuario u = new Usuario();
            u.setEmail(email);
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            if (usuarioDAO.desativarUsuario(u)) {
                Toast.makeText(this, "Usuario Suspenso com sucesso", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);
            } else
                Toast.makeText(this, "Não foi possivel deletar o usuario", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Senha ou e-mail divergente da conta do usuario", Toast.LENGTH_SHORT).show();

    }

    public void fecharAct(View v){
        finish();
    }
}