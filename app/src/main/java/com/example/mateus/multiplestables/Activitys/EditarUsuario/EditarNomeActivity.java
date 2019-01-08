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

import java.util.ArrayList;

public class EditarNomeActivity extends AppCompatActivity {

    String usuario_email;
    EditText edt_editarNome;
    ArrayList<Integer> idAnunciosCarrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_nome);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            usuario_email      = bundle.getString("usuario_email");
            idAnunciosCarrinho = bundle.getIntegerArrayList("carrinho");
        }
        edt_editarNome = findViewById(R.id.edt_editarNome);
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

    public void editarNome(View view){
        String novoNome = edt_editarNome.getText().toString();
        AlertDialog.Builder confirmarEditar = new AlertDialog.Builder(this);
        confirmarEditar.setTitle("Confirmação");
        confirmarEditar
                .setIcon(R.mipmap.ic_aviso)
                .setMessage("Você quer mudar o nome para '" + novoNome + "' ")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String novoNome = edt_editarNome.getText().toString();
                        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
                        usuarioDAO.editarNomeUsuario(usuario_email, novoNome);
                        Toast.makeText(EditarNomeActivity.this, "Nome editado com sucesso", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EditarNomeActivity.this, "Escolha um melhor", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alertDialog = confirmarEditar.create();
        alertDialog.show();


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
