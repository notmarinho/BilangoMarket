package com.example.mateus.bilangomarket;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mateus.bilangomarket.DATA.UsuarioDAO;

public class Cadastro extends AppCompatActivity {

    EditText edt_nome;
    EditText edt_senha;
    EditText edt_email;
    EditText edt_confSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edt_nome      = (EditText)findViewById(R.id.edt_nome);
        edt_email     = (EditText)findViewById(R.id.edt_email);
        edt_senha     = (EditText)findViewById(R.id.edt_senha);
        edt_confSenha = (EditText)findViewById(R.id.edt_confSenha);

    }

    private void cadastrar(){
        Usuario u = new Usuario();
        u.setNome(edt_nome.getText().toString());
        u.setEmail(edt_email.getText().toString());
        u.setSenha(edt_senha.getText().toString());
        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        usuarioDAO.inserirUsuario(u);
        Toast.makeText(this, "Usuario inserido com sucesso", Toast.LENGTH_SHORT).show();
    }


    public void deletarUsuario(View v){
        Usuario u = new Usuario();
        u.setNome (edt_nome.getText().toString());
        u.setEmail(edt_email.getText().toString());
        u.setSenha(edt_senha.getText().toString());

        UsuarioDAO delete = new UsuarioDAO(getApplicationContext());

        if(delete.desativarUsuario(u)){
            Toast.makeText(this, "Usuario deletado com sucesso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Usuario nao encontrado", Toast.LENGTH_SHORT).show();
        }
    }



    public void validarCampos(View v){   /*   ESTA FUNÇAO ESTA ATRIBUIDA AO BOTAO CADASTRAR ( POR ENQUANTO ) */
        boolean res = false;
        String nome       = edt_nome.getText().toString();
        String email      = edt_email.getText().toString();
        String senha      = edt_senha.getText().toString();
        String confSenha  = edt_confSenha.getText().toString();

        if (res = isCampoVazio(nome)){
            Toast.makeText(this, "Por favor, informe seu nome", Toast.LENGTH_LONG).show();
            edt_nome.requestFocus();
        }
        else
            if(res = !isEmailValido(email)){
                Toast.makeText(this, "E-mail inválido", Toast.LENGTH_LONG).show();
                edt_email.requestFocus();
            }else
                if(res = isCampoVazio(senha)){
                    Toast.makeText(this, "Por favor, informe uma senha", Toast.LENGTH_LONG).show();
                    edt_senha.requestFocus();
                }else
                    if(res = !senha.equals(confSenha)){
                        Toast.makeText(this, "As senhas não são iguais", Toast.LENGTH_LONG).show();
                        edt_confSenha.requestFocus();
                    }else
                        if(res = senha.length() < 5 || senha.length() > 15){
                            Toast.makeText(this, "Sua tem que ter entre 5 a 15 caractéres", Toast.LENGTH_LONG).show();
                            edt_senha.requestFocus();
                        }
        if (!res){
            cadastrar();
            finish();
        }
    }

    public void fechar_act(View v){
        finish();
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
