package com.example.mateus.bilangomarket.Acitivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mateus.bilangomarket.Acitivitys.Cadastro;
import com.example.mateus.bilangomarket.Acitivitys.ListaUsuarios;
import com.example.mateus.bilangomarket.Acitivitys.LoginActivity;
import com.example.mateus.bilangomarket.DATA.AjudanteBD;
import com.example.mateus.bilangomarket.DATA.UsuarioDAO;
import com.example.mateus.bilangomarket.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void act_cadastro(View view){
        Intent it = new Intent(this, Cadastro.class);
        startActivity(it);
    }
    public void act_login(View v){
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
    }
}
