package com.example.mytimesheet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipal extends AppCompatActivity {

    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        textViewName = (TextView) findViewById(R.id.tv_name);

        /*String nameParam = savedInstanceState.getString("DATA_NAME_KEY");
        textViewName.setText(nameParam);*/

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

    public void cambiarClave(View v){

        startActivity(new Intent(this,CambiarClave.class));

    }

    public void altausuarios(View v){

        startActivity(new Intent(this,AltaUsuarios.class));

    }
}