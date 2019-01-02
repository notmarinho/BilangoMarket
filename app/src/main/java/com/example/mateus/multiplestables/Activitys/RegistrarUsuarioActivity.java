package com.example.mateus.multiplestables.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mateus.multiplestables.DATA.UsuarioDAO;
import com.example.mateus.multiplestables.R;
import com.example.mateus.multiplestables.Usuario;

import java.util.ArrayList;

public class RegistrarUsuarioActivity extends AppCompatActivity {

    EditText edt_nome;
    EditText edt_email;
    EditText edt_senha;
    EditText edt_confSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        edt_nome      = (EditText)findViewById(R.id.edt_usuarioNome);
        edt_email     = (EditText)findViewById(R.id.edt_usuarioEmail);
        edt_senha     = (EditText)findViewById(R.id.edt_usuarioSenha);
        edt_confSenha = (EditText)findViewById(R.id.edt_usuarioConfirmarSenha);
    }

    public void cadastrarUsuario(View view){
        boolean erro = false;
        String nome       = edt_nome.getText().toString();
        String email      = edt_email.getText().toString();
        String senha      = edt_senha.getText().toString();
        String confSenha  = edt_confSenha.getText().toString();
        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());

        if (erro = isCampoVazio(nome)){
            Toast.makeText(this, "Por favor, informe seu nome", Toast.LENGTH_LONG).show();
            edt_nome.requestFocus();
        }
        else
        if(erro = !isEmailValido(email)){
            Toast.makeText(this, "E-mail inválido", Toast.LENGTH_LONG).show();
            edt_email.requestFocus();
        }else
        if(erro = isCampoVazio(senha)){
            Toast.makeText(this, "Por favor, informe uma senha", Toast.LENGTH_LONG).show();
            edt_senha.requestFocus();
        }else
        if(erro = !senha.equals(confSenha)){
            Toast.makeText(this, "As senhas não são iguais", Toast.LENGTH_LONG).show();
            edt_confSenha.requestFocus();
        }else
        if(erro = senha.length() < 5 || senha.length() > 15){
            Toast.makeText(this, "Sua tem que ter entre 5 a 15 caractéres", Toast.LENGTH_LONG).show();
            edt_senha.requestFocus();
        }else
        if (erro = isEmailCadastrado(email)){
            Toast.makeText(this, "E-mail ja cadastrado", Toast.LENGTH_SHORT).show();
            edt_email.requestFocus();
            }

        if (!erro){
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuarioDAO.inserirUsuario(usuario);
            Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public boolean isEmailCadastrado(String email){
        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        Usuario usuario = usuarioDAO.getUsuarioByEmail(email);
        if (usuario != null){
            return true;
        }else return false;
    }

    private boolean isEmailValido(String email){
        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

    private boolean isCampoVazio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());  /* Verificando se o campo esta vazio*/
        return resultado;
    }



}
