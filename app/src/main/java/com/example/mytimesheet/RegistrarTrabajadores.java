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

public class RegistrarTrabajadores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_trabajadores);
    }

    public void registraTrabajador(View v){


        apiRegistraTrabajador();
        startActivity(new Intent(this, GestorTrabajadores.class));

    }
    private void apiRegistraTrabajador(){

        //Capturar los valores que se ingresarán en el API
        final EditText txtDNI = findViewById(R.id.et_DNI);
        final EditText txtNombreTrabajador = findViewById(R.id.et_nombreTrabajador);
        final EditText txtApellidoTrabajador = findViewById(R.id.et_apellidoTrabajador);
        final EditText txtSueldo = findViewById(R.id.et_Sueldo);
        final EditText txtCorreo = findViewById(R.id.et_correo);
        final EditText txtEstado = findViewById(R.id.et_estadoTrabajador);

        //URL Servicio JSON
        String url = "https://serviciosts.azurewebsites.net/api/Workers/GetRegWorkers";

        //Declarar para pasar el valor en número
        Integer Estado = Integer.parseInt(txtEstado.getText().toString());
        Integer DNI = Integer.parseInt(txtDNI.getText().toString());
        double Sueldo = Integer.parseInt(txtSueldo.getText().toString());
        String NombTrabajador = txtNombreTrabajador.getText().toString();
        String ApellTrabajador = txtApellidoTrabajador.getText().toString();
        String Correo = txtCorreo.getText().toString();

        //Declaración de la estructura JSON
        JSONObject jsonobject = new JSONObject();
        try {

            jsonobject.put("nu_DNI",DNI);
            jsonobject.put("no_Nombres",NombTrabajador);
            jsonobject.put("no_Apellidos",ApellTrabajador);
            jsonobject.put("ss_Sueldo",Sueldo);
            jsonobject.put("tx_Correo",Correo);
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

                        Toast toast = Toast.makeText(RegistrarTrabajadores.this,"Se envió correctamente", Toast.LENGTH_LONG);

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