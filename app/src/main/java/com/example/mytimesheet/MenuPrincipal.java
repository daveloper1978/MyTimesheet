package com.example.mytimesheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mytimesheet.listas.Menu;
import com.example.mytimesheet.listas.MenuAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuPrincipal extends AppCompatActivity {

    private TextView textViewName;

    private ArrayList<Menu> menuList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MenuAdapter mAdapter;
    String nameParam, nameParam1, nameParam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        recyclerView = findViewById(R.id.rv_menu);
        mAdapter = new MenuAdapter(menuList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),menuList.get(recyclerView.getChildAdapterPosition(view)).getDescripcion(),Toast.LENGTH_LONG).show();

                String packageName = getPackageName();
                String className = menuList.get(recyclerView.getChildAdapterPosition(view)).getMenuValor();

                elegirMenu(packageName,className,nameParam1);
            }
        });

        recyclerView.setAdapter(mAdapter);

        nameParam = getIntent().getStringExtra("DNI");
        nameParam1 = getIntent().getStringExtra("USUARIO");
        nameParam2 = getIntent().getStringExtra("ESTADO");
        Log.i("======4>", nameParam + nameParam1 + nameParam2 );
        textViewName = findViewById(R.id.tv_name);
        textViewName.setText(nameParam1);

        listarMenu(Integer.parseInt(nameParam));

        if (nameParam2.equals("2")) {

            Intent intent = new Intent(getApplicationContext(), CambiarClave.class);

            intent.putExtra("DNI", nameParam);
            startActivity(intent);

        }

    }

    private void listarMenu(int DNI) {

        String url = "https://serviciosts.azurewebsites.net/api/Menu/GetMenuResponse";

        final JSONObject jsonobject = new JSONObject();
        try {
            jsonobject.put("nu_dni", DNI);
            Log.i("======>", jsonobject.toString());
        } catch (JSONException e) {

            Log.i("======>", e.getMessage());

        }

        JsonObjectRequest requerimiento= new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonobject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            int size = jsonArray.length();
                            Menu menu;
                            Log.i("Clear======>", String.valueOf(size));

                            for (int i=0; i<size; i++){

                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                final String sdescripcion = jsonObject.getString("no_Descripcion");
                                final String smenuValor = jsonObject.getString("tx_Valor");
                                menu = new Menu(sdescripcion,smenuValor);
                                menuList.add(menu);
                            }

                            mAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.i("======>", error.toString());

                    }

                } );

        MySingleton.getInstance(this).addToRequestQueue(requerimiento);


    }

    public void Salir(View v){

        startActivity(new Intent(this,MainActivity.class));

    }

    public void elegirMenu(String packageName, String className, String usuario){

        Intent intent = new Intent();
        intent.setClassName(packageName,packageName+className);
        intent.putExtra("USUARIO", usuario);

        startActivity(intent);

    }

}