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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button ingresar;
    EditText name, password;
    final String[] sDNI = new String[1];
    final String[] sEstado = new String[1];

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

            Toast.makeText(MainActivity.this, "getResources().getString(R.string.welcome)", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
            intent.putExtra("DATA_NAME_KEY", name.getText().toString());

            //validarLogin();
            startActivity(intent);

        }


    }

    public void recuperar(View v){

        startActivity(new Intent(this, RecuperarCredenciales.class));

    }

    private void validarLogin(){

        //URL Servicio JSON
        String url = "https://serviciosts.azurewebsites.net/api/Credential/GetCredentialResponse";

        Integer DNI = Integer.parseInt(name.getText().toString());

        JSONObject jsonobject = new JSONObject();
        try {
            jsonobject.put("nu_dni", DNI);
            jsonobject.put("co_clave", password.getText().toString());
            Log.i("======>", jsonobject.toString());
        } catch (JSONException e) {

            Log.i("======>", e.getMessage());

        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url,
                jsonobject,
                new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) { //Success Callback

                    try {
                        JSONArray jsonarray = response.getJSONArray("Data");
                        int size = jsonarray.length();
                        for (int i=0; i<size; i++){

                            JSONObject jsonObject = new JSONObject(jsonarray.get(i).toString());
                            sDNI[i] = jsonObject.getString("nu_DNI");
                            sEstado[i] = jsonObject.getString("co_estado");

                        }
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

    }

    private void onResponse() {

        //URL Servicio JSON
        String url = "https://serviciosts.azurewebsites.net/api/Credential/GetCredentialResponse";

        Integer DNI = Integer.parseInt(name.getText().toString());

        JSONObject jsonobject = new JSONObject();
        try {
            jsonobject.put("nu_dni", DNI);
            jsonobject.put("co_clave", password.getText().toString());
            Log.i("======>", jsonobject.toString());
        } catch (JSONException e) {
            Log.i("======>", e.getMessage());
        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                int size = response.length();
                for (int i = 0; i < size; i++) {

                    try {
                        JSONObject jsonObject = new JSONObject(response.get(i).toString());
                        sDNI[i] = jsonObject.getString("nu_DNI");
                        sEstado[i] = jsonObject.getString("co_estado");

                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }

                }
            }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("======>", error.getMessage());
                }
            });


    }
}