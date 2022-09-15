package com.example.mytimesheet;

import static android.view.Gravity.CENTER;

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

public class AltaUsuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_usuarios);
    }

    public void altaUsuario(View v){

        //Función para consumir API
        apiAltaUsuario();
        startActivity(new Intent(this, MenuPrincipal.class));

    }

    private void apiAltaUsuario(){

        //Capturar los valores que se ingresarán en el API
        final EditText txtNombre = findViewById(R.id.et_usuario);

        //URL Servicio JSON
        String url = "https://serviciosts.azurewebsites.net/api/Users/GetRegUsers";

        //Declarar para pasar el valor en número
        Integer DNI = Integer.parseInt(txtNombre.getText().toString());

        //Declaración de la estructura JSON
        JSONObject jsonobject = new JSONObject();
        try {
            jsonobject.put("nu_dni", DNI);
            jsonobject.put("co_clave", "1234");
            jsonobject.put("co_estado", 2);
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
                        Toast toast = Toast.makeText(AltaUsuarios.this,"Se envió correctamente", Toast.LENGTH_LONG);
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