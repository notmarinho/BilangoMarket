package com.example.mateus.multiplestables.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mateus.multiplestables.Anuncio;
import com.example.mateus.multiplestables.DATA.AnuncioDAO;
import com.example.mateus.multiplestables.DATA.AnunciosAdapter;
import com.example.mateus.multiplestables.DATA.UsuarioDAO;
import com.example.mateus.multiplestables.Menu_deslizante;
import com.example.mateus.multiplestables.R;
import com.example.mateus.multiplestables.Usuario;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CarrinhoActivity extends AppCompatActivity {

    String usuario_email;
    TextView txt_precoTotal;
    ListView listView;
    ArrayList<Integer> idAnunciosCarrinho;
    ArrayList<Anuncio> anunciosCarrinho = new ArrayList<>();
    float preco = 0;
    Usuario comprador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        txt_precoTotal = findViewById(R.id.txt_precoTotal);
        listView       = findViewById(R.id.listViewCarrinho);
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            usuario_email      = bundle.getString("usuario_email");
            idAnunciosCarrinho = bundle.getIntegerArrayList("carrinho");
            for (int i = 0; i < idAnunciosCarrinho.size(); i++){
                AnuncioDAO anuncioDAO = new AnuncioDAO(getApplicationContext());
                Anuncio anuncio = anuncioDAO.getAnuncioByID(idAnunciosCarrinho.get(i));
                anunciosCarrinho.add(anuncio);
                preco += anuncio.getPreço();
            }
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            comprador = usuarioDAO.getUsuarioByEmail(usuario_email);
            DecimalFormat df = new DecimalFormat("0.00");
            String total  = df.format(preco);
            txt_precoTotal.setText(total);
            ListView listView = findViewById(R.id.listViewCarrinho);
            ArrayAdapter adapter = new AnunciosAdapter(this, anunciosCarrinho);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    ArrayList<Integer> lista = getCarrinho();
                    final int anuncio_ID  = lista.get(position);

                    AlertDialog.Builder removarAnuncio = new AlertDialog.Builder(CarrinhoActivity.this);
                    removarAnuncio.setTitle("Aviso");
                    removarAnuncio
                            .setIcon(R.mipmap.ic_aviso)
                            .setMessage("Voce deseja remover este anuncio do carrinho?")
                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    idAnunciosCarrinho.remove(position);
                                    Bundle b = new Bundle();
                                    b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
                                    Intent it = new Intent(getApplicationContext(), CarrinhoActivity.class);
                                    it.putExtra("usuario_email", usuario_email);
                                    it.putExtras(b);
                                    startActivity(it);
                                    finish();
                                }
                            })
                            .setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    AlertDialog alertDialog = removarAnuncio.create();
                    alertDialog.show();
                }
            });
        }
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    public ArrayList<Integer> getCarrinho(){
        return idAnunciosCarrinho;
    }

    public void comprarTudo(View view){
        if (idAnunciosCarrinho.size() > 0) {
            AnuncioDAO anuncioDAO = new AnuncioDAO(getApplicationContext());
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            for (int i = 0; i < idAnunciosCarrinho.size(); i++) {
                Anuncio anuncio    = anuncioDAO.getAnuncioByID(idAnunciosCarrinho.get(i));
                Usuario vendedor   = usuarioDAO.getUsuarioByID(anuncio.getDonoID());

                int anuncios_vendidos = vendedor.getAnuncios_vendidos() + 1;
                float total_recebido  = vendedor.getTotal_recebido() + anuncio.getPreço();
                usuarioDAO.declararAnuncioVendido(vendedor.getEmail(), anuncios_vendidos);
                usuarioDAO.declararTotalRecebido(vendedor.getEmail(), total_recebido);

                int anuncios_comprados = comprador.getAnuncios_comprados() + 1;
                float total_gasto      = comprador.getTotal_gasto() + anuncio.getPreço();
                usuarioDAO.declararAnuncioComprado(comprador.getEmail(), anuncios_comprados);
                usuarioDAO.declararTotalGasto(comprador.getEmail(), total_gasto);
                anuncioDAO.deleteAnuncio(idAnunciosCarrinho.get(i));
            }
            idAnunciosCarrinho.clear();
            Toast.makeText(this, "Compra Efetuada com sucesso", Toast.LENGTH_SHORT).show();
            Bundle b = new Bundle();
            b.putIntegerArrayList("carrinho", idAnunciosCarrinho);
            Intent it = new Intent(this, Menu_deslizante.class);
            it.putExtras(b);
            it.putExtra("usuario_email", usuario_email);
            finish();
            Menu_deslizante.menu_deslizante.finish();
            startActivity(it);

        }else {
            Toast.makeText(this, "Adicione algum item ao carrinho para comprar, bilango demais...", Toast.LENGTH_SHORT).show();
        }

    }


}
