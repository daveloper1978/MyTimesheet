package com.example.mytimesheet;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class consulta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        String[] from = new String[] { "Time", "Name", "Desc" };
        int[] to = new int[] { R.id.hora, R.id.nombre, R.id.desc1 };

        ArrayList<String[]> lista = new ArrayList<String[]>();

        String[] evento1 = { "11:30", "Reunión de coordinación", "Lugar: Auditorio", "1" };
        String[] evento2 = { "12:00", "Almuerzo", "Lugar: Comedor central", "2" };
        String[] evento3 = { "14:00", "Tiro al dardo", "Lugar: Oficina", "3" };
        lista.add(evento1);
        lista.add(evento2);
        lista.add(evento3);

        ArrayList<HashMap<String, String>> eventos = new ArrayList<HashMap<String, String>>();
        for (String[] evento : lista) { HashMap<String, String> datosEvento = new HashMap<String, String>();
            datosEvento.put("Time", evento[0]);
            datosEvento.put("Name", evento[1]);
            datosEvento.put("Desc", evento[2]);
            datosEvento.put("id", evento[3]);
            eventos.add(datosEvento);
        }
        SimpleAdapter listadoAdapter = new SimpleAdapter(this, eventos, R.layout.activity_consulta_horas_consultor, from, to);
        ListView lv = (ListView) findViewById(R.id.lista1);
        lv.setAdapter(listadoAdapter);
    }
}