package com.example.mytimesheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mytimesheet.listas.Clientes;
import com.example.mytimesheet.listas.ClientesAdapter;
import com.example.mytimesheet.listas.Proyectos;
import com.example.mytimesheet.listas.Trabajadores;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GestorTrabajadores extends AppCompatActivity {

    Button btn_AddOne;
    private ArrayList<Trabajadores> trabajadoresList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TrabajadoresAdapter mAdapter;
    private TextView textViewName;


    recyclerView =

    findViewById(R.id.lv_trabajadores);

    mAdapter =new

    TrabajadoresAdapter(trabajadoresList);

    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new

    DefaultItemAnimator());

        recyclerView.addItemDecoration(new

    DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);

    String nameParam = getIntent().getStringExtra("USUARIO");
    textViewName =

    findViewById(R.id.tv_name);
        textViewName.setText(nameParam);

    ListarTrabajadores();

    btn_AddOne =

    findViewById(R.id.btn_AddOne);

        btn_AddOne.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        Intent intent = new Intent(getApplicationContext(), RegistrarActividades.class);
        startActivity(intent);
    }
    });
}

    private void ListarTrabajadores() {

        String url = "https://serviciosts.azurewebsites.net/api/Workers/GetListWorkers";

        JsonObjectRequest requerimiento= new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            int size = jsonArray.length();
                            Trabajadores trabajadores;
                            Log.i("Clear======>", String.valueOf(size));

                            for (int i=0; i<size; i++){

                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                final String nombTrabajador = jsonObject.getString("no_Nombres");
                                final String apellTrabajador = jsonObject.getString("no_Apellidos");
                                final double sueldoTrabajador = jsonObject.getString("ss_Sueldo");
                                trabajadores = new Trabajadores(nombTrabajador,apellTrabajador,sueldoTrabajador);
                                trabajadoresList.add(trabajadores);
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor_trabajadores);
    }

    public void Salir(View v){

        startActivity(new Intent(this,MenuPrincipal.class));

    }
}