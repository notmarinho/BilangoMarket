package com.example.mateus.bilangomarket.Acitivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mateus.bilangomarket.DATA.UsuarioDAO;
import com.example.mateus.bilangomarket.R;

public class EditarSenha extends AppCompatActivity {

    String usuario_senha;
    String usuario_email;
    EditText edt_senhaAtual;
    EditText edt_novaSenha;
    EditText edt_novaSenhaConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_senha);
        edt_senhaAtual = (EditText)findViewById(R.id.edt_senhaAtual);
        edt_novaSenha = (EditText)findViewById(R.id.edt_novaSenha);
        edt_novaSenhaConf = (EditText)findViewById(R.id.edt_confNovaSenha);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            usuario_senha = bundle.getString("usuario_senha");
            usuario_email = bundle.getString("usuario_email");
        }
    }

    public void alterarSenha(View view){
        boolean erro = false;
        String senhaAtual = edt_senhaAtual.getText().toString();
        String novaSenha  = edt_novaSenha.getText().toString();
        String novaSenhaConf = edt_novaSenhaConf.getText().toString();

        while (true){
            if (!usuario_senha.equals(senhaAtual)){
                erro = true;
                Toast.makeText(this, "A senha inserida não corresponde a senha atual", Toast.LENGTH_SHORT).show();
                break;
            }else if (novaSenha.length() < 5){
                erro = true;
                Toast.makeText(this, "A nova senha tem que ter no minimo 5 caracteres", Toast.LENGTH_SHORT).show();
                break;
            }else if (!novaSenha.equals(novaSenhaConf)){
                erro = true;
                Toast.makeText(this, "Há divergencia entre a senha inseria e a confirmação", Toast.LENGTH_SHORT).show();
                break;
            }else break;
        }
        if (erro == false){
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            usuarioDAO.editarSenha(usuario_email, edt_novaSenha.getText().toString());
            Toast.makeText(this, "Senha alterada com sucesso", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void fecharAct(View view){
        Intent it = new Intent(this, MenuEditarUsuario.class);
        startActivity(it);
        finish();
    }

}

