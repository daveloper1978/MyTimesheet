package com.example.mytimesheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CambiarClave extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_clave);
    }

    public void grabarClave(View v){

        //Función para consumir API
        apiCambioClave();
        startActivity(new Intent(this, MainActivity.class));

    }

    private void apiCambioClave(){

        //Capturar los valores que se ingresarán en el API
        final EditText txtNombre = findViewById(R.id.et_usuario);
        final EditText txtClave = findViewById(R.id.et_clave);

        //URL Servicio JSON
        String url = "https://serviciosts.azurewebsites.net/api/Users/GetRegUsers";

        //Declarar para pasar el valor en número
        Integer DNI = Integer.parseInt(txtNombre.getText().toString());

        //Declaración de la estructura JSON
        JSONObject jsonobject = new JSONObject();
        try {
            jsonobject.put("nu_dni", DNI);
            jsonobject.put("co_clave", txtClave.getText().toString());
            jsonobject.put("co_estado", 1);
            jsonobject.put("s_Type", "Update");
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

                        Toast toast = Toast.makeText(CambiarClave.this,"Se envió correctamente", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
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