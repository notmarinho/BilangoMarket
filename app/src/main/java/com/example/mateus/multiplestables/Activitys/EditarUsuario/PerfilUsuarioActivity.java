package com.example.mateus.multiplestables.Activitys.EditarUsuario;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mateus.multiplestables.DATA.UsuarioDAO;
import com.example.mateus.multiplestables.Menu_deslizante;
import com.example.mateus.multiplestables.R;
import com.example.mateus.multiplestables.Usuario;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PerfilUsuarioActivity extends AppCompatActivity {

    public static Activity menuActivity;
    String usuario_email;
    TextView texto_NomeUsuario;
    TextView txt_anunciosVendidos;
    TextView txt_anunciosComprados;
    TextView txt_totalGasto;
    TextView txt_totalRecebido;

    Usuario usuario;
    ArrayList<Integer> idAnunciosCarrinho;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        menuActivity = this;

        texto_NomeUsuario     = findViewById(R.id.txt_NomeUsuario);
        txt_anunciosComprados = findViewById(R.id.txt_anunciosComprados);
        txt_anunciosVendidos  = findViewById(R.id.txt_anunciosVendidos);
        txt_totalGasto        = findViewById(R.id.txt_totalGasto);
        txt_totalRecebido     = findViewById(R.id.txt_totalRecebido);

        Bundle bundle = getIntent().getExtras();

        if (bundle!= null){
            usuario_email = bundle.getString("usuario_email");
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            usuario = usuarioDAO.getUsuarioByEmail(usuario_email);
            idAnunciosCarrinho = bundle.getIntegerArrayList("carrinho");
            texto_NomeUsuario.setText(usuario.getNome());
            DecimalFormat df = new DecimalFormat("0.00");
            String gastos  = df.format(usuario.getTotal_gasto());
            String recebidos = df.format(usuario.getTotal_recebido());
            txt_anunciosComprados.setText(String.valueOf(usuario.getAnuncios_comprados()));
            txt_anunciosVendidos.setText(String.valueOf(usuario.getAnuncios_vendidos()));
            txt_totalGasto.setText(gastos);
            txt_totalRecebido.setText(recebidos);
        }
    }
    @Override
    public void onBackPressed(){
        Intent it = new Intent(this, Menu_deslizante.class);
        Bundle b = new Bundle();
        b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
        it.putExtra("usuario_email", usuario_email);
        it.putExtras(b);
        startActivity(it);
        finish();
    }


   public void act_editarUsuarioMenu(View view){
        Bundle b = new Bundle();
        b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
        Intent it = new Intent(this, EditarUsuarioMenuActivity.class);
        it.putExtra("usuario_email", usuario_email);
        it.putExtras(b);
        startActivity(it);
        finish();
    }

    public void act_menuDeslizante(View view){
        Intent it = new Intent(this, Menu_deslizante.class);
        Bundle b = new Bundle();
        b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
        it.putExtra("usuario_email", usuario_email);
        it.putExtras(b);
        startActivity(it);
        finish();
    }
}
