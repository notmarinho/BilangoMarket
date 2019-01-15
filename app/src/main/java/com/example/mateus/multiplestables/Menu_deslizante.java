package com.example.mateus.multiplestables;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.github.clans.fab.FloatingActionButton;

import com.example.mateus.multiplestables.Activitys.CarrinhoActivity;
import com.example.mateus.multiplestables.Activitys.ItemClicadoActivity;
import com.example.mateus.multiplestables.Activitys.EditarAnuncio.ListaDeAnunciosActivity;
import com.example.mateus.multiplestables.Activitys.MainActivity;
import com.example.mateus.multiplestables.Activitys.EditarUsuario.PerfilUsuarioActivity;
import com.example.mateus.multiplestables.Activitys.RegistrarAnunciosActivity;
import com.example.mateus.multiplestables.DATA.AnuncioDAO;
import com.example.mateus.multiplestables.DATA.AnunciosAdapter;
import com.example.mateus.multiplestables.DATA.UsuarioDAO;

import java.util.ArrayList;

public class Menu_deslizante extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    String usuario_email;
    Usuario usuario;
    TextView txt_usuarioNome;
    TextView txt_usuarioEmail;
    public static Activity menu_deslizante;
    ArrayList<Integer> idAnunciosCarrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_deslizante);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menu_deslizante = this;

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        txt_usuarioNome  = headerView.findViewById(R.id.txt_nav_usuarioNome);
        txt_usuarioEmail = headerView.findViewById(R.id.txt_nav_usuarioEmail);

        Bundle bundle = getIntent().getExtras();

        if (bundle!= null){
            idAnunciosCarrinho = bundle.getIntegerArrayList("carrinho");
            usuario_email      = bundle.getString("usuario_email");

            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            usuario = usuarioDAO.getUsuarioByEmail(usuario_email);
            txt_usuarioNome.setText(usuario.getNome());
            txt_usuarioEmail.setText(usuario_email);
        }

        FloatingActionButton fab = findViewById(R.id.fab);                   //Botão flutuante
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
                Intent it = new Intent(getApplicationContext(), RegistrarAnunciosActivity.class);
                it.putExtras(b);
                it.putExtra("usuario_email", usuario_email);
                startActivity(it);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        ListView listView = findViewById(R.id.listViewLogado);
        final AnuncioDAO anuncioDAO = new AnuncioDAO(getApplicationContext());
        ArrayAdapter adapter = new AnunciosAdapter(this, anuncioDAO.getAllAnuncios());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Anuncio> lista = anuncioDAO.getAllAnuncios();
                String anuncio_nome      = lista.get(position).getNome();
                String anuncio_preco     = lista.get(position).getPrecoString();
                String anuncio_descricao = lista.get(position).getDescricao();
                int    anuncio_ID        = lista.get(position).getID();
                int    anunciante        = lista.get(position).getDonoID();
                Bundle b  = new Bundle();
                b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
                Intent it = new Intent(getApplicationContext(), ItemClicadoActivity.class);
                it.putExtras(b);
                it.putExtra("anuncio_nome",      anuncio_nome);                            // Enviando os dados para a Activity que aparecera todos os dados do anuncio
                it.putExtra("anuncio_preco",     anuncio_preco);
                it.putExtra("anuncio_descricao", anuncio_descricao);
                it.putExtra("anuncio_ID",        anuncio_ID);
                it.putExtra("usuario_email",     usuario_email);
                it.putExtra("anunciante",        anunciante);
                startActivity(it);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder deslogar = new AlertDialog.Builder(this);
        deslogar.setTitle("Aviso");
        deslogar
                .setIcon(R.mipmap.ic_aviso)
                .setMessage("Voce deseja deslogar?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent it = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(it);
                        finish();
                    }
                })
                .setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = deslogar.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deslizante, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_carrinho) {
            Bundle b = new Bundle();
            b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
            Intent it = new Intent(this, CarrinhoActivity.class);
            it.putExtra("usuario_email", usuario_email);
            it.putExtras(b);
            startActivity(it);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Bundle b = new Bundle();
            b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
            Intent it = new Intent(this, PerfilUsuarioActivity.class);
            it.putExtras(b);
            it.putExtra("usuario_email", usuario_email);
            startActivity(it);
            finish();

        } else if (id == R.id.nav_carrinho) {
            Bundle b = new Bundle();
            b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
            Intent it = new Intent(this, CarrinhoActivity.class);
            it.putExtra("usuario_email", usuario_email);
            it.putExtras(b);
            startActivity(it);

        } else if (id == R.id.nav_usuarioAnuncios) {
            Bundle b = new Bundle();
            b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
            Intent it = new Intent(this, ListaDeAnunciosActivity.class);
            it.putExtra("usuario_email", usuario_email);
            it.putExtras(b);
            startActivity(it);

        } else if (id == R.id.nav_deslogar) {
            AlertDialog.Builder deslogar = new AlertDialog.Builder(this);
            deslogar.setTitle("Aviso");
            deslogar
                    .setIcon(R.mipmap.ic_aviso)
                    .setMessage("Voce deseja deslogar?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent it = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(it);
                            finish();
                        }
                    })
                    .setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            AlertDialog alertDialog = deslogar.create();
            alertDialog.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
