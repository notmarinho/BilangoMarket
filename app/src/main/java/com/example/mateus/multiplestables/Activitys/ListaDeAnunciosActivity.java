package com.example.mateus.multiplestables.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mateus.multiplestables.Anuncio;
import com.example.mateus.multiplestables.DATA.AnuncioDAO;
import com.example.mateus.multiplestables.DATA.UsuarioDAO;
import com.example.mateus.multiplestables.R;
import com.example.mateus.multiplestables.Usuario;

import java.util.ArrayList;

public class ListaDeAnunciosActivity extends AppCompatActivity {
    String usuario_email;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_anuncios);
        ListView listView = (ListView)findViewById(R.id.listView);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            usuario_email = bundle.getString("usuario_email");
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            usuario = usuarioDAO.getUsuarioByEmail(usuario_email);
        }

        ArrayList<String> listaAnunciosNomes = new ArrayList<>();
        AnuncioDAO anuncioDAO = new AnuncioDAO(getApplicationContext());
        ArrayList<Anuncio> listaAnuncios = anuncioDAO.getAllAnuncios();

        if (listaAnuncios.size() == 0){
            Toast.makeText(this, "Não há anuncio cadastrado no banco", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            for (int i = 0; i < listaAnuncios.size() ; i++){
                Anuncio anuncio = listaAnuncios.get(i);
                if (anuncio.getDonoID() == usuario.getID()){
                    listaAnunciosNomes.add(anuncio.getNome());
                }
            }
            if (listaAnunciosNomes.size() == 0){
                Toast.makeText(this, "Você não tem anuncios cadastrados", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaAnunciosNomes);
                listView.setAdapter(arrayAdapter);
            }
        }
    }

    public void fechar_act(View view){
        finish();
    }
}
