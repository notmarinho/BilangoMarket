package com.example.mateus.multiplestables.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mateus.multiplestables.Activitys.RegistrarUsuarioActivity;
import com.example.mateus.multiplestables.R;

public class MainActivity extends AppCompatActivity {
    public static Activity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
    }


    public void act_cadastrarUsuario(View view){
        Intent it = new Intent(this, RegistrarUsuarioActivity.class);
        startActivity(it);
    }

    public void act_logar(View view){
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
    }
}
