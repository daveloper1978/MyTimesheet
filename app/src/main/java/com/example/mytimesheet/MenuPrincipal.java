package com.example.mytimesheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipal extends AppCompatActivity {

    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        String nameParam = getIntent().getStringExtra("DNI");
        String nameParam2 = getIntent().getStringExtra("ESTADO");
        Log.i("======4>", nameParam + nameParam2 );
        textViewName = findViewById(R.id.tv_name);
        textViewName.setText(nameParam);

        if (nameParam2.equals("2")) {

            Intent intent = new Intent(getApplicationContext(), CambiarClave.class);

            intent.putExtra("DNI", nameParam);
            startActivity(intent);

        }

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

    public void registraActividad(View v){

        startActivity(new Intent(this, RegistrarActividades.class));

    }

    public void registraCliente(View v){

        startActivity(new Intent(this, RegistrarCliente.class));

    }
}