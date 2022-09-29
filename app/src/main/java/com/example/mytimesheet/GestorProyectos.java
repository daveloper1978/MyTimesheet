package com.example.mytimesheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import com.example.mytimesheet.listas.Proyectos;
import com.example.mytimesheet.listas.ProyectosAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GestorProyectos extends AppCompatActivity {


    Button btn_AddOne;
    private ArrayList<Proyectos> proyectosList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProyectosAdapter mAdapter;
    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor_proyectos);

        recyclerView = findViewById(R.id.lv_clientes);
        mAdapter = new ProyectosAdapter(proyectosList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);

        String nameParam = getIntent().getStringExtra("USUARIO");
        textViewName = findViewById(R.id.tv_name);
        textViewName.setText(nameParam);

        ListarProyectos();

        btn_AddOne = findViewById(R.id.btn_AddOne);

        btn_AddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrarActividades.class);
                startActivity(intent);
            }
        });


    }

    private void ListarProyectos() {

        String url = "https://serviciosts.azurewebsites.net/api/Project/GetProjects";

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
                            Proyectos proyectos;
                            Log.i("Clear======>", String.valueOf(size));

                            for (int i=0; i<size; i++){

                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                final String codProyecto = jsonObject.getString("co_Codigo");
                                final String nombreProyecto = jsonObject.getString("no_Proyecto");
                                proyectos = new Proyectos(codProyecto,nombreProyecto);
                                proyectosList.add(proyectos);
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

        startActivity(new Intent(this,MenuPrincipal.class));

    }
}