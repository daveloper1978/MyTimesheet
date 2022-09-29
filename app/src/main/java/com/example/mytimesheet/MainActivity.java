package com.example.mytimesheet;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button ingresar;
    private EditText name, password;

        //  create a textWatcher member
    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues();
        }
    };

    void checkFieldsForEmptyValues(){
        ingresar = (Button) findViewById(R.id.bt_ingresar);

        String s1 = name.getText().toString();
        String s2 = password.getText().toString();

        if(s1.equals("")|| s2.equals("")){
            ingresar.setEnabled(false);
        } else {
            ingresar.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        // set listeners
        name.addTextChangedListener(mTextWatcher);
        password.addTextChangedListener(mTextWatcher);

        // run once to disable if empty
        checkFieldsForEmptyValues();

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logear();
            }
        });

    }

    private void initUI(){

        name = findViewById(R.id.editTextTextUser);
        password = findViewById(R.id.editTextTextPassword);

    }

    public void logear(){

        if (name.length()==0){

            name.setError(getResources().getString(R.string.invalid_username));

        }
        else if (password.length()==0){

            password.setError(getResources().getString(R.string.invalid_password));

        }
        else {

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
                                final String susuario = data.getString("no_usuario");
                                final String sestado = data.getString("co_estado");

                                Log.i("======1>", sdni);
                                Log.i("======2>", susuario);
                                Log.i("======3>", sestado);

                                Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);

                                intent.putExtra("DNI", sdni);
                                intent.putExtra("USUARIO", susuario);
                                intent.putExtra("ESTADO", sestado);

                                if (response.getString("issuccess") == "true") {
                                    startActivity(intent);

                                    Toast toast = Toast.makeText(MainActivity.this,"¡Bienvenido!. Login correcto", Toast.LENGTH_LONG);
                                    toast.show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("======6>", error.getMessage());
                            Toast toast = Toast.makeText(MainActivity.this,"Error en la validación de credenciales", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    } );

            MySingleton.getInstance(this).addToRequestQueue(jsonObjReq);
            
        }

    }

    public void recuperar(View v){

        startActivity(new Intent(this, RecuperarCredenciales.class));

    }

}