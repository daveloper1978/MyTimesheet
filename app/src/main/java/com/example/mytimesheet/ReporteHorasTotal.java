package com.example.mytimesheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mytimesheet.listas.Reporte;
import com.example.mytimesheet.listas.ReporteAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReporteHorasTotal extends AppCompatActivity {

    private ArrayList<Reporte> reporteList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReporteAdapter mAdapter;
    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_horas_total);

        recyclerView = findViewById(R.id.lv_reportehoras);
        mAdapter = new ReporteAdapter(reporteList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);

        String nameParam = getIntent().getStringExtra("USUARIO");
        String nameParam1 = getIntent().getStringExtra("DNI");
        String nameParam2 = getIntent().getStringExtra("FEC_INI");
        String nameParam3 = getIntent().getStringExtra("FEC_FIN");
        Log.i("======4>", nameParam + nameParam1 + nameParam2 + nameParam3);


        textViewName = findViewById(R.id.tv_name);
        textViewName.setText(nameParam);

        ReporteHoras(Integer.parseInt(nameParam1),nameParam2,nameParam3);

    }

    private void ReporteHoras(int DNI, String Fec_ini, String Fec_fin){

        String url = "https://serviciosts.azurewebsites.net/api/Hours/GetHoursResponse";

        final JSONObject jsonobject = new JSONObject();
        try {
            jsonobject.put("fe_Registro_ini", Fec_ini);
            jsonobject.put("fe_Registro_fin", Fec_fin);
            jsonobject.put("nu_dni", DNI);

            Log.i("======>", jsonobject.toString());
        } catch (JSONException e) {

            Log.i("======>", e.getMessage());

        }

        JsonObjectRequest requerimiento= new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonobject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            int size = jsonArray.length();
                            Reporte reporte;
                            Log.i("Clear======>", String.valueOf(size));

                            for (int i=0; i<size; i++){

                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                final String sferegistro = jsonObject.getString("fe_Registro");
                                final String snoproyecto = jsonObject.getString("no_Proyecto");
                                final Double snhoras = Double.parseDouble(jsonObject.getString("nu_HorasTrabajador"));

                                reporte = new Reporte(sferegistro,snoproyecto,snhoras);
                                reporteList.add(reporte);
                            }

                            mAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.i("======>", error.toString());

                    }

                } );

        MySingleton.getInstance(this).addToRequestQueue(requerimiento);

    }

    public void Salir(View v){

        startActivity(new Intent(this,ReporteHoras.class));

    }
}