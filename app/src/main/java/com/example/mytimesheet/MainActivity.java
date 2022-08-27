package com.example.mytimesheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logear(View v){

        startActivity(new Intent(this, MenuPrincipal.class));

    }

    public void recuperar(View v){

        startActivity(new Intent(this, RecuperarCredenciales.class));

    }

}