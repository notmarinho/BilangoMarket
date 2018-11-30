package com.example.mateus.bilangomarket;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mateus.bilangomarket.CRUD.Create;
import com.example.mateus.bilangomarket.CRUD.Delete;
import com.example.mateus.bilangomarket.CRUD.Read;
import com.example.mateus.bilangomarket.CRUD.Update;

import java.util.ArrayList;

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

        Create c = new Create(getApplicationContext());
        c.createTable();
    }

    private void cadastrar(){
        Usuario u = new Usuario();
        u.setNome(edt_nome.getText().toString());
        u.setEmail(edt_email.getText().toString());
        u.setSenha(edt_senha.getText().toString());

        Update update = new Update(getApplicationContext());
        if(update.inserirUsuario(u)){
            Toast.makeText(this, "Usuario inserido com sucesso", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Usuario nao inserido", Toast.LENGTH_SHORT).show();
        }
    }

    public void editarUsuario(View v){
        Usuario u = new Usuario();
        u.setNome(edt_nome.getText().toString());
        u.setEmail(edt_email.getText().toString());
        u.setSenha(edt_senha.getText().toString());

        Update update = new Update(getApplicationContext());
        if(update.editarUsuario(u)){
            Toast.makeText(this, "Usuario editado com sucesso", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Ocorreu algum erro", Toast.LENGTH_SHORT).show();
        }
    }

    public void deletarUsuario(View v){
        Usuario u = new Usuario();
        u.setNome (edt_nome.getText().toString());
        u.setEmail(edt_email.getText().toString());
        u.setSenha(edt_senha.getText().toString());

        Delete delete = new Delete(getApplicationContext());

        if(delete.desativarUsuario(u)){
            Toast.makeText(this, "Usuario deletado com sucesso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Usuario nao encontrado", Toast.LENGTH_SHORT).show();
        }

    }

    public void deletarTable(View v){
        Delete deletar  = new Delete(getApplicationContext());
        deletar.deleteTable();
    }

    public void lerTabela(View v){

        Read r = new Read(getApplicationContext());
        ArrayList<Usuario> uArray = r.getUsuario();

        for (int i = 0; i < uArray.size() ; i++) {
            Usuario u = uArray.get(i);
            System.out.println("NOME: " + u.getNome() +
                    " \nEMAIL: " + u.getEmail() +
                    " \nSENHA: " + u.getSenha() +
                    " \nSTATUS: " + u.getAtivo());
        }
    }

    public void validarCampos(View v){   /*   ESTA FUNÇAO ESTA ATRIBUIDA AO BOTAO CADASTRAR ( POR ENQUANTO ) */
        boolean res = false;
        String nome       = edt_nome.getText().toString();
        String email      = edt_email.getText().toString();
        String senha      = edt_senha.getText().toString();
        String confSenha  = edt_confSenha.getText().toString();

        if (res = isCampoVazio(nome)){
            edt_nome.requestFocus();
        }
        else
            if(res = !isEmailValido(email)){
                edt_email.requestFocus();
            }else
                if(res = isCampoVazio(senha)){
                    edt_senha.requestFocus();
                }else
                    if(res = !senha.equals(confSenha)){
                        edt_confSenha.requestFocus();
                    }
        if (res){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Há campos inválidos ou em branco");
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }else{
            cadastrar();
        }
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
