package com.example.mytimesheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class RegistrarActividades extends AppCompatActivity {

    private static final int CENTER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_actividades);
    }

    public void registraActividad(View v){

        // registraActividad();
        apiRegistraActividad();
        startActivity(new Intent(this, MenuPrincipal.class));

    }
    private void apiRegistraActividad(){

        //Capturar los valores que se ingresarán en el API
        final EditText txtActividad = findViewById(R.id.et_nombreCliente);
        final EditText txtNombreActividad = findViewById(R.id.et_actividadNombre);

        //URL Servicio JSON
        String url = "https://serviciosts.azurewebsites.net/api/Activity/GetRegActivity";

        //Declarar para pasar el valor en número
        Integer ACTIVIDAD = Integer.parseInt(txtActividad.getText().toString());
        String ActividadNombre = txtNombreActividad.getText().toString();

        //Declaración de la estructura JSON
        JSONObject jsonobject = new JSONObject();
        try {
            jsonobject.put("co_Actividad", ACTIVIDAD);
            jsonobject.put("no_Actividad",ActividadNombre);
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

                        Toast toast = Toast.makeText(RegistrarActividades.this,"Se envió correctamente", Toast.LENGTH_LONG);
                        toast.setGravity(CENTER, 0, 0);
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