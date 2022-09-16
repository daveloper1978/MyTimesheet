package com.example.mytimesheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {

    Button ingresar;
    EditText name, password;
    String Est2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editTextTextUser);
        password = findViewById(R.id.editTextTextPassword);

    }

    public void logear(View v){

        if (name.length()==0){

           name.setError(getResources().getString(R.string.invalid_username));

        }
        else if (password.length()==0){

            password.setError(getResources().getString(R.string.invalid_password));

        }
        else {

            final String sValor = validarLogin();
            Toast.makeText(MainActivity.this, getResources().getString(R.string.welcome), Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
            Log.i("======>",name.getText().toString());
            intent.putExtra("DNI", name.getText().toString());
            intent.putExtra("ESTADO", Est2);

            startActivity(intent);

        }

    }

    public void recuperar(View v){

        startActivity(new Intent(this, RecuperarCredenciales.class));

    }

    public String validarLogin(){

        final String[] Est1 = new String[1];

        //URL Servicio JSON
        String url = "https://serviciosts.azurewebsites.net/api/Credential/GetCredentialResponse";

        Integer DNI = Integer.parseInt(name.getText().toString());

        final JSONObject jsonobject = new JSONObject();
        try {
            jsonobject.put("nu_dni", DNI);
            jsonobject.put("co_clave", password.getText().toString());
            Log.i("======>", jsonobject.toString());
        } catch (JSONException e) {

            Log.i("======>", e.getMessage());

        }

        final JsonObjectRequest jsonObjReq = new
                JsonObjectRequest(Request.Method.POST,
                url,
                jsonobject,
                new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) { //Success Callback
                    /*
                    try {
                        final String datos = response.getString("data");
                        Log.i("======>", datos);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/

                    try {
                        JSONObject data = response.getJSONObject("data");
                        final String sdni = data.getString("nu_DNI");
                        final String sestado = data.getString("co_estado");
                        Est2 = data.getString("co_estado");
                        Log.i("======2>", sdni);
                        Log.i("======>", sestado);
                        Log.i("======3>", Est2);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Toast toast = Toast.makeText(MainActivity.this,"Se logeó correctamente", Toast.LENGTH_LONG);
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

        return Est2;

    }

}