package com.example.mytimesheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.mytimesheet.listas.Clientes;
import com.example.mytimesheet.listas.ClientesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GestorClientes extends AppCompatActivity {

    Button btn_AddOne;
    private ArrayList<Clientes> clientesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ClientesAdapter mAdapter;
    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor_clientes);

        recyclerView = findViewById(R.id.lv_trabajadores);
        mAdapter = new ClientesAdapter(clientesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);

        String nameParam = getIntent().getStringExtra("USUARIO");
        textViewName = findViewById(R.id.tv_name);
        textViewName.setText(nameParam);

        ListarClientes();

        btn_AddOne = findViewById(R.id.btn_AddOne);

        btn_AddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrarCliente.class);
                startActivity(intent);
            }
        });


    }

    //Invoca servicio para llenar actividades
    private void ListarClientes() {

        String url = "https://serviciosts.azurewebsites.net/api/Client/GetListClient";

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
                            Clientes clientes;
                            Log.i("Clear======>", String.valueOf(size));

                            for (int i=0; i<size; i++){

                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                final String sRUC = jsonObject.getString("co_RUC");
                                final String snombreEmp = jsonObject.getString("no_Nombre");
                                clientes = new Clientes(sRUC,snombreEmp);
                                clientesList.add(clientes);
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

    //Regresa al Menu Principal
    public void Salir(View v){

        startActivity(new Intent(this,MenuPrincipal.class));

    }
}