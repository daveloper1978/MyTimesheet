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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RecuperarCredenciales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_credenciales);

    }

    private void volver(View v){

        startActivity(new Intent(this,MenuPrincipal.class));

    }

    public void enviar(View v){

        recuperarClave();
        startActivity(new Intent(this, MainActivity.class));

    }

    private void recuperarClave(){

        final EditText txtNombre = findViewById(R.id.et_usuario);

        //URL Servicio JSON
        String url = "https://serviciosts.azurewebsites.net/api/Users/GetRegUsers";

        Integer DNI = Integer.parseInt(txtNombre.getText().toString());

        JSONObject jsonobject = new JSONObject();
        try {
            jsonobject.put("nu_dni", DNI);
            jsonobject.put("co_clave", "1234");
            jsonobject.put("co_estado", 2);
            jsonobject.put("s_Type", "Update");
            Log.i("======>", jsonobject.toString());
        } catch (JSONException e) {

            Log.i("======>", e.getMessage());

        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
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

                    Toast toast = Toast.makeText(RecuperarCredenciales.this,"Se envió correctamente", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("======>", error.getMessage());

            }
        } );

        MySingleton.getInstance(this).addToRequestQueue(jsonObjReq);

    }
}