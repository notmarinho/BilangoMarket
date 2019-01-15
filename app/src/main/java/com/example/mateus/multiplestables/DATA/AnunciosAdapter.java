package com.example.mateus.multiplestables.DATA;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mateus.multiplestables.Anuncio;
import com.example.mateus.multiplestables.DATA.UsuarioDAO;
import com.example.mateus.multiplestables.R;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AnunciosAdapter  extends ArrayAdapter<Anuncio> {
    private final Context context;
    private ArrayList<Anuncio> elementos;

    public AnunciosAdapter(Context context, ArrayList<Anuncio> elementos){
        super(context, R.layout.layout_anuncios, elementos);
        this.context   = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.layout_anuncios, parent, false);

        TextView nomeAnuncio  = rowView.findViewById(R.id.nomeAnuncio);
        TextView precoAnuncio = rowView.findViewById(R.id.precoAnuncio);

        DecimalFormat df = new DecimalFormat("0.00");
        String total     = df.format(elementos.get(position).getPreço());

        nomeAnuncio.setText(elementos.get(position).getNome());
        precoAnuncio.setText(total);
        return rowView;
    }
}
