package com.example.mytimesheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RecuperarCredenciales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_credenciales);
    }

    public void enviar(View v){

        startActivity(new Intent(this, MainActivity.class));

    }
}