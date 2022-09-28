package com.example.mytimesheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrarCliente extends AppCompatActivity {

    private static final int CENTER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);
    }

    public void registraCliente(View v){

        // registraActividad();
        apiRegistraCliente();
        startActivity(new Intent(this, MenuPrincipal.class));

    }
    private void apiRegistraCliente(){

        //Capturar los valores que se ingresarán en el API
        final EditText txtRuc = findViewById(R.id.et_ruc);
        final EditText txtNombEmpresa = findViewById(R.id.et_nombreCliente);
        final EditText txtActividad = findViewById(R.id.et_actividadCliente);
        final EditText txtEstado = findViewById(R.id.et_estadoCli);

        //URL Servicio JSON
        String url = "https://serviciosts.azurewebsites.net/api/Client/GetRegClient";

        //Declarar para pasar el valor en número
        Integer CodActividad = Integer.parseInt(txtActividad.getText().toString());
        Integer CodEstado = Integer.parseInt(txtEstado.getText().toString());

        //Declaración de la estructura JSON
        JSONObject jsonobject = new JSONObject();
        try {
            jsonobject.put("co_RUC",txtRuc.getText().toString());
            jsonobject.put("no_Nombre", txtNombEmpresa.getText().toString());
            jsonobject.put("no_Actividad",CodActividad);
            jsonobject.put("no_Estado",CodEstado);
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

                        Toast toast = Toast.makeText(RegistrarCliente.this,"Se guardó correctamente", Toast.LENGTH_LONG);
                        toast.setGravity(CENTER, 0, 0);
                        toast.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("======>", error.getMessage());
            }
        } );

        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);*/

        MySingleton.getInstance(this).addToRequestQueue(jsonObjReq);

    }
}
