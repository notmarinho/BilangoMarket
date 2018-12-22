package com.example.mateus.bilangomarket.Acitivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mateus.bilangomarket.DATA.UsuarioDAO;
import com.example.mateus.bilangomarket.R;

public class EditarNomeUsuario extends AppCompatActivity {

    EditText edt_confimarEmail;
    EditText edt_novoNomeUsuario;
    String usuario_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_nome_usuario);
        edt_confimarEmail   = (EditText)findViewById(R.id.edt_confirmarEmail);
        edt_novoNomeUsuario = (EditText)findViewById(R.id.edt_novoNomeUsuario);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            usuario_email = bundle.getString("usuario_email");
        }
    }

    public void alterarNomeUsuario(View view){
        String confirmarEmail  = edt_confimarEmail.getText().toString();
        String novoNomeUsuario = edt_novoNomeUsuario.getText().toString();
        if (!confirmarEmail.equals(usuario_email)){
            Toast.makeText(this, "O email do usuario não corresponde ao email inserido", Toast.LENGTH_SHORT).show();
        }else {
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            usuarioDAO.editarNomeUsuario(usuario_email, novoNomeUsuario);
            Toast.makeText(this, "Nome do usuario editado com sucesso", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(this, MenuUsuario.class);
            it.putExtra("usuario_email", usuario_email);
            startActivity(it);
            finish();
        }
    }

    public void fecharAct(View view){
        Intent it = new Intent(this, MenuEditarUsuario.class);
        startActivity(it);
        finish();
    }
}
