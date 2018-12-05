package com.example.mateus.bilangomarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mateus.bilangomarket.CRUD.Read;

import java.util.ArrayList;

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
    public void act_listaUsuarios(View v){
        Intent it = new Intent(this, ListaUsuarios.class);
        Read r = new Read(getApplicationContext());
        ArrayList<Usuario> uArray = r.getUsuario();
        if (uArray == null){
            Toast.makeText(this, "Não há usuários cadastrados.", Toast.LENGTH_SHORT).show();
        }
        else{
            System.out.println("Entrou no ELSE");
            startActivity(it);}
    }
}