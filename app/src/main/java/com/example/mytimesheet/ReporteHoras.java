package com.example.mytimesheet;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ReporteHoras extends AppCompatActivity {

    ImageButton bfecha;
    TextView efecha, textViewName;
    private int dia, mes, anno;
    String usuario, dni, Fecini,Fecfin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_horas);

        usuario = getIntent().getStringExtra("USUARIO");
        dni = getIntent().getStringExtra("DNI");

        textViewName = findViewById(R.id.tv_name);
        textViewName.setText(usuario);


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


    public void Selfecha(View v){


        final Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        anno = c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                String fecha = i2+"-"+i1+"-"+i;

                if(v.getId() == R.id.ibt_cal2){
                    bfecha = (ImageButton)findViewById(R.id.ibt_cal2);
                    efecha = (TextView)findViewById(R.id.etd_fecini);
                    efecha.setText(fecha);
                    Fecini = efecha.getText().toString();
                }
                if(v.getId() == R.id.ibt_cal3){
                    bfecha = (ImageButton)findViewById(R.id.ibt_cal3);
                    efecha = (TextView)findViewById(R.id.etd_fecfin);
                    efecha.setText(fecha);
                    Fecfin = efecha.getText().toString();
                }

            }
        }
                ,dia,mes,anno);
        datePickerDialog.show();

    }
}