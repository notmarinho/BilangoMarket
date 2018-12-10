package com.example.mateus.bilangomarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CadastrarAnuncio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_anuncio);
    }

    public void fechar_act(View view){
        finish();
    }
}
