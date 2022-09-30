package com.example.mytimesheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrarProyecto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_proyecto);
    }

    public void registraProyecto(View v){

        // registraActividad();
        apiRegistraProyecto();
        startActivity(new Intent(this, GestorProyectos.class));

    }
    private void apiRegistraProyecto(){

        //Capturar los valores que se ingresarán en el API
        final EditText txtCodigo = findViewById(R.id.et_codProyecto);
        final EditText txtNombreProyecto = findViewById(R.id.et_nombreProyecto);
        final EditText txtCentroCosto = findViewById(R.id.et_Proyecto);
        final EditText txtEstado = findViewById(R.id.et_estadoPro);

        //URL Servicio JSON
        String url = "https://serviciosts.azurewebsites.net/api/Project/GetRegProjects";

        //Declarar para pasar el valor en número
        Integer Estado = Integer.parseInt(txtEstado.getText().toString());
        Integer CentroCosto = Integer.parseInt(txtCentroCosto.getText().toString());
        String ProyectoNombre = txtNombreProyecto.getText().toString();
        String CodigoProyecto = txtCodigo.getText().toString();

        //Declaración de la estructura JSON
        JSONObject jsonobject = new JSONObject();
        try {

            jsonobject.put("co_Codigo",CodigoProyecto);
            jsonobject.put("no_Proyecto",ProyectoNombre);
            jsonobject.put("co_CentroCosto",CentroCosto);
            jsonobject.put("co_Estado",Estado);
            jsonobject.put("s_Type", "Insert");
            Log.i("======>", jsonobject.toString());
        } catch (JSONException e) {

            Log.i("======>", e.getMessage());

        }

        //Ejecución del SERVICIO WEB
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url,
                jsonobject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) { //Success Callback

                        try {
                            final String datos = response.getString("issuccess");
                            Log.i("======>", datos);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast toast = Toast.makeText(RegistrarProyecto.this,"Se envió correctamente", Toast.LENGTH_LONG);

                        toast.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("======>", error.getMessage());
            }
        } );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);

    }
}