package com.example.mytimesheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void Salir(View v){
        startActivity(new Intent(this,MainActivity.class));

    }

    public void consultar_reporte(View v){
        startActivity(new Intent(this,ReporteHoras.class));

    }

    public void registrarhoras(View v){
        startActivity(new Intent(this,Controlhoras.class));

    }
}