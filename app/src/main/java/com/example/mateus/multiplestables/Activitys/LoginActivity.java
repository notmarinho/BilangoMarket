package com.example.mateus.multiplestables.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mateus.multiplestables.DATA.DBHelper;
import com.example.mateus.multiplestables.DATA.UsuarioDAO;
import com.example.mateus.multiplestables.Menu_deslizante;
import com.example.mateus.multiplestables.R;
import com.example.mateus.multiplestables.Usuario;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText edt_login_email;
    EditText edt_login_senha;

    ArrayList<Integer> idAnunciosCarrinho = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_login_email = findViewById(R.id.edt_login_email);
        edt_login_senha = findViewById(R.id.edt_login_senha);
    }

    public boolean ativarUsuario(){
        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        if (usuarioDAO.ativarUsuario(edt_login_email.getText().toString())){
            return true;
        }else return false;

    }
    public void logar(View view){
        final UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        final String login_email = edt_login_email.getText().toString();
        String login_senha       = edt_login_senha.getText().toString();

        Usuario usuario = usuarioDAO.getUsuarioByEmail(login_email);
        if (usuario != null){
            if (usuario.getStatus() == 0){
                AlertDialog.Builder reativarUsuario = new AlertDialog.Builder(this);
                reativarUsuario
                        .setTitle("Aviso")
                        .setIcon(R.mipmap.ic_aviso)
                        .setMessage("Usuario desativo, voce deseja reativalo?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (ativarUsuario()){
                                    Toast.makeText(LoginActivity.this, "Usuario ativado novamente", Toast.LENGTH_SHORT).show();
                                    Bundle b  = new Bundle();
                                    b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
                                    Intent it = new Intent(getApplicationContext(), Menu_deslizante.class);
                                    it.putExtras(b);
                                    it.putExtra("usuario_email", login_email);
                                    startActivity(it);
                                    overridePendingTransition(android.R.anim.linear_interpolator, android.R.anim.fade_out);
                                    MainActivity.mainActivity.finish(); // Fechando a activity Principal
                                    finish(); //Fechando a acitiviy atual
                                }else
                                    Toast.makeText(LoginActivity.this, "Usuario não ativado", Toast.LENGTH_SHORT).show();

                            }
                        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialog = reativarUsuario.create();
                alertDialog.show();

            }
            else if (usuario.getSenha().equals(login_senha)){
                Bundle b  = new Bundle();
                b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
                Intent it = new Intent(getApplicationContext(), Menu_deslizante.class);
                it.putExtras(b);
                it.putExtra("usuario_email", login_email);
                startActivity(it);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                MainActivity.mainActivity.finish();                                 // Fechando a activity Principal
                finish();                                                           // Fechando a acitiviy atual

            }else {
                Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show();
                edt_login_senha.requestFocus();
            }
        }else {
            Toast.makeText(this, "Email não cadastrado", Toast.LENGTH_SHORT).show();
            edt_login_email.requestFocus();
        }
    }

    public void act_cadastro(View view){
        Intent it = new Intent(this, RegistrarUsuarioActivity.class);
        startActivity(it);
        finish();

    }

}
