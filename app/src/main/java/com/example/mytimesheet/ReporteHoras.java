package com.example.mytimesheet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class ReporteHoras extends AppCompatActivity {

    ImageButton bfecha;
    TextView efecha;
    private int dia, mes, anno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_horas);
    }

    public void Menu(View v){
        startActivity(new Intent(this, MenuPrincipal.class ));

    }

    public void ejecutaconsulta(View v){
        startActivity(new Intent(this, consulta.class ));

    }


    public void Selfecha(View v){

        if(v.getId() == R.id.ibt_cal2){
            bfecha = (ImageButton)findViewById(R.id.ibt_cal2);
            efecha = (TextView)findViewById(R.id.etd_fecini);
        }
        if(v.getId() == R.id.ibt_cal3){
            bfecha = (ImageButton)findViewById(R.id.ibt_cal3);
            efecha = (TextView)findViewById(R.id.etd_fecfin);
        }


        final Calendar c = Calendar.getInstance();
        final int dia = c.get(Calendar.DAY_OF_MONTH);
        final int mes = c.get(Calendar.MONTH);
        final int anno = c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                String fecha = i2+"/"+i1+"/"+i;
                efecha.setText(fecha);
            }
        }
                ,dia,mes,anno);
        datePickerDialog.show();

    }
}