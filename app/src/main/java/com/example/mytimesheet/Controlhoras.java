package com.example.mytimesheet;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Controlhoras extends AppCompatActivity {

    Button btn_AddOne;
    TextView efecha, textViewName;
    String Fecini, dni, usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlhoras);

        usuario = getIntent().getStringExtra("USUARIO");
        dni = getIntent().getStringExtra("DNI");

        textViewName = findViewById(R.id.tv_name);
        textViewName.setText(usuario);

        efecha = (TextView)findViewById(R.id.tv_fecha);

        efecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_fecha:
                        showDatePickerDialog();
                        break;
                }
            }
        });

        btn_AddOne = findViewById(R.id.btn_AddOne);

       /* btn_AddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrarActividades.class);
                startActivity(intent);
            }
        });*/

    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                efecha.setText(selectedDate);
                String cadena = "/"+(month+101);
                cadena = cadena.substring(2,4);
                Fecini = year + "-" + cadena + "-" + day;
                Log.i("======Fecha>", Fecini );


            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public void Menu(View v){

        startActivity(new Intent(this, MenuPrincipal.class ));

    }

}