package com.example.mytimesheet;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReporteHoras extends AppCompatActivity  {

    TextView textViewName;
    private int tipo;
    String usuario, dni, Fecini,Fecfin;

    EditText etFecIni,etFecFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_horas);

        usuario = getIntent().getStringExtra("USUARIO");
        dni = getIntent().getStringExtra("DNI");

        textViewName = findViewById(R.id.tv_name);
        textViewName.setText(usuario);

        etFecIni = findViewById(R.id.etd_fecini);

        etFecIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etd_fecini:
                        tipo = 0;
                        showDatePickerDialog();
                        break;
                }
            }
        });

        etFecFin = findViewById(R.id.etd_fecfin);

        etFecFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etd_fecfin:
                        tipo = 1;
                        showDatePickerDialog();
                        break;
                }
            }
        });

    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;

                if (tipo == 0){
                    etFecIni.setText(selectedDate);
                    String cadena = "/"+(month+101);
                    cadena = cadena.substring(2,4);
                    Fecini = year + "-" + cadena + "-" + day;
                    Log.i("======FechaIni>", Fecini );
                }else if (tipo == 1){
                    etFecFin.setText(selectedDate);
                    String cadena = "/"+(month+101);
                    cadena = cadena.substring(2,4);
                    Fecfin = year + "-" + cadena + "-" + day;
                    Log.i("======FechaFin>", Fecfin );
                }

            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public void Menu(View v){
        startActivity(new Intent(this, MenuPrincipal.class ));
    }

    public void ejecutaconsulta(View v){

        Intent intent = new Intent(getApplicationContext(), ReporteHorasTotal.class);

        intent.putExtra("DNI", dni);
        intent.putExtra("USUARIO", usuario);
        intent.putExtra("FEC_INI", Fecini);
        intent.putExtra("FEC_FIN", Fecfin);

        startActivity(intent);

    }

}