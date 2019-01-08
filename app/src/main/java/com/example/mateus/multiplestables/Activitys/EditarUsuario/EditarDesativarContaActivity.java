package com.example.mateus.multiplestables.Activitys.EditarUsuario;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mateus.multiplestables.Activitys.MainActivity;
import com.example.mateus.multiplestables.DATA.UsuarioDAO;
import com.example.mateus.multiplestables.R;
import com.example.mateus.multiplestables.Usuario;

import java.util.ArrayList;

public class EditarDesativarContaActivity extends AppCompatActivity {
    String usuario_email;
    Usuario usuario;

    EditText edt_emailDesativar;
    EditText edt_senhaDesativar;

    ArrayList<Integer> idAnunciosCarrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_desativar_conta);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            usuario_email = bundle.getString("usuario_email");
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            usuario = usuarioDAO.getUsuarioByEmail(usuario_email);
            idAnunciosCarrinho = bundle.getIntegerArrayList("carrinho");
        }
        edt_emailDesativar = findViewById(R.id.edt_emailDesativar);
        edt_senhaDesativar = findViewById(R.id.edt_senhaDesativar);
        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        usuario = usuarioDAO.getUsuarioByEmail(usuario_email);
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

    public void desativarUsuario(View view){
        final UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        String email = edt_emailDesativar.getText().toString();
        String senha = edt_senhaDesativar.getText().toString();

        if (email.equals(usuario_email)){
            if (senha.equals(usuario.getSenha())){
                AlertDialog.Builder desativar = new AlertDialog.Builder(this);
                desativar
                        .setTitle("Aviso")
                        .setIcon(R.mipmap.ic_aviso)
                        .setMessage("Deseja desativar esta conta?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                usuarioDAO.desativarUsuario(usuario_email);
                                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(it);
                                PerfilUsuarioActivity.menuActivity.finish();
                                EditarUsuarioMenuActivity.editarUsuarioActivity.finish();
                                Toast.makeText(EditarDesativarContaActivity.this, "Conta desativada com sucesso", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = desativar.create();
                alertDialog.show();
            }
        }else {
            Toast.makeText(this, "Email ou senha divergentes da conta", Toast.LENGTH_SHORT).show();
            edt_emailDesativar.requestFocus();
        }
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
