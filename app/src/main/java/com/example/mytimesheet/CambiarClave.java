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

public class CambiarClave extends AppCompatActivity {

    Button ingresar;
    private EditText name, password, password2;

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
        ingresar = (Button) findViewById(R.id.bt_enviar);

        String s1 = name.getText().toString();
        String s2 = password.getText().toString();
        String s3 = password2.getText().toString();

        if(s1.equals("")|| s2.equals("")|| s3.equals("")){
            ingresar.setEnabled(false);
        } else {
            ingresar.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_clave);

        String nameParam = getIntent().getStringExtra("DNI");

        initUI();
        name.setText(nameParam);

        // set listeners
        name.addTextChangedListener(mTextWatcher);
        password.addTextChangedListener(mTextWatcher);
        password2.addTextChangedListener(mTextWatcher);

        // run once to disable if empty
        checkFieldsForEmptyValues();

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabarClave();
            }
        });
    }

    private void initUI(){

        name = findViewById(R.id.et_usuario);
        password = findViewById(R.id.et_clave);
        password2 = findViewById(R.id.et_clave2);

    }

    public void grabarClave(){

        //Función para consumir API
        apiCambioClave();
        startActivity(new Intent(this, MainActivity.class));


    }

    private void apiCambioClave(){

        //URL Servicio JSON
        String url = "https://serviciosts.azurewebsites.net/api/Users/GetRegUsers";

        //Declarar para pasar el valor en número
        Integer DNI = Integer.parseInt(name.getText().toString());

        //Declaración de la estructura JSON
        JSONObject jsonobject = new JSONObject();
        try {
            jsonobject.put("nu_dni", DNI);
            jsonobject.put("co_clave", password.getText().toString());
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

                        Toast toast = Toast.makeText(CambiarClave.this,"Se modificó la clave correctamente", Toast.LENGTH_LONG);
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