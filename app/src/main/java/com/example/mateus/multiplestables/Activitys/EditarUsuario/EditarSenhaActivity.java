package com.example.mateus.multiplestables.Activitys.EditarUsuario;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mateus.multiplestables.DATA.UsuarioDAO;
import com.example.mateus.multiplestables.R;
import com.example.mateus.multiplestables.Usuario;

import java.util.ArrayList;

public class EditarSenhaActivity extends AppCompatActivity {

    EditText edt_senhaAtual;
    EditText edt_novaSenha;
    EditText edt_confNovaSenha;
    String usuario_email;
    Usuario usuario;

    ArrayList<Integer> idAnunciosCarrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_senha);
        edt_senhaAtual    = (EditText)findViewById(R.id.edt_senhaAtual);
        edt_novaSenha     = (EditText)findViewById(R.id.edt_novaSenha);
        edt_confNovaSenha = (EditText)findViewById(R.id.edt_cofirNovaSenha);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            usuario_email = bundle.getString("usuario_email");
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            usuario = usuarioDAO.getUsuarioByEmail(usuario_email);
            idAnunciosCarrinho= bundle.getIntegerArrayList("carrinho");
        }
    }

    @Override
    public void onBackPressed(){
        Bundle b = new Bundle();
        b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
        Intent it = new Intent(this, EditarUsuarioMenuActivity.class);
        it.putExtras(b);
        it.putExtra("usuario_email", usuario_email);
        startActivity(it);
        finish();
    }

    public void editarSenha(View view){
        String senhaAtual       = edt_senhaAtual.getText().toString();
        final String novaSenha  = edt_novaSenha.getText().toString();
        String confNovaSenha    = edt_confNovaSenha.getText().toString();

        if (senhaAtual.equals(usuario.getSenha())){
            if (novaSenha.equals(confNovaSenha)){
                AlertDialog.Builder confirmarEditar = new AlertDialog.Builder(this);
                confirmarEditar.setTitle("Confirmação");
                confirmarEditar
                        .setIcon(R.mipmap.ic_aviso)
                        .setMessage("Tem certeza de que quer mudar a senha?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
                                usuarioDAO.editarSenha(usuario_email, novaSenha);
                                Toast.makeText(EditarSenhaActivity.this, "Senha editada com sucesso", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                AlertDialog alertDialog = confirmarEditar.create();
                alertDialog.show();


            }else Toast.makeText(this, "A nova senha nao é igual a confirmaçao", Toast.LENGTH_SHORT).show();
        }else Toast.makeText(this, "A senha inserida não condiz com a do usuario", Toast.LENGTH_SHORT).show();
    }

    public void act_menuUsuario(View view){
        Bundle b = new Bundle();
        b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
        Intent it = new Intent(this, EditarUsuarioMenuActivity.class);
        it.putExtras(b);
        it.putExtra("usuario_email", usuario_email);
        startActivity(it);
        finish();
    }


}
