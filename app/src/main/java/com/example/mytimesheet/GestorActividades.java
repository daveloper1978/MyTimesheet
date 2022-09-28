package com.example.mytimesheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mytimesheet.listas.Actividades;
import com.example.mytimesheet.listas.ActividadesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GestorActividades extends AppCompatActivity {

    Button btn_AddOne;
    private ArrayList<Actividades> actividadesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ActividadesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor_actividades);

        recyclerView = findViewById(R.id.lv_actividades);
        mAdapter = new ActividadesAdapter(actividadesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);

        ListarActividades();

        btn_AddOne = findViewById(R.id.btn_AddOne);

        btn_AddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrarActividades.class);
                startActivity(intent);
            }
        });
    }

    private void ListarActividades() {

    String url = "https://serviciosts.azurewebsites.net/api/Activity/GetListActivity";

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
                    Actividades actividades;
                    Log.i("Clear======>", String.valueOf(size));

                    for (int i=0; i<size; i++){

                        JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                        final String sNomActividad = jsonObject.getString("no_Actividad");
                        final int iActividad = Integer.parseInt(jsonObject.getString("co_Actividad"));
                        actividades = new Actividades(iActividad,sNomActividad);
                        actividadesList.add(actividades);
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
}