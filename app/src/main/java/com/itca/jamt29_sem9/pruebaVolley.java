package com.itca.jamt29_sem9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class pruebaVolley extends AppCompatActivity {
    private TextView txtres;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_volley);

        txtres = findViewById(R.id.textView);
        btn = findViewById(R.id.btn);
    }

    private void baseRequest() {
        String URl_PRUEBA = "https://mjgl.com.sv/pruebaVolley/test.ph";
        StringRequest request = new StringRequest(Request.Method.GET, URl_PRUEBA, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }

        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("Accept", "application/json");
                map.put("id", "1");
                return map;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


    private void pruebaVolley() {
        String url = "https://mjgl.com.sv/pruebaVolley/test.php";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response.substring(0, 16));
                       // txtres.setText(response.substring(0, 16));
                        txtres.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Something went wrong!");
                Toast.makeText(getApplicationContext(), "Sin conexion a Internet", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }


  /* private void peticionJson(){
       String url = "https://mjgl.com.sv/pruebaVolley/test.php";
      //  String url1 = URL_PRUEBA;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, (String) null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        txtres.setText("Response: " + response.toString());
                        Toast.makeText(getApplicationContext(), "" + response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                        @Override
                                public  void onErrorResponse(VolleyError error) {
                            //TODO: Handle error
                        }

                });
       MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);

    } */
    private void recibirJson(){
        String url = "https://mjgl.com.sv/pruebaVolley/test.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuestaJSON = new JSONObject(response.toString());
                    String var1 = respuestaJSON.getString("itca");
                    String var2 = respuestaJSON.getString("nombre");
                    String var3 = respuestaJSON.getString("Categoria");
                    txtres.setText("Response: " + response.toString());
                    Toast.makeText(getApplicationContext(), "ITCA" + var1 + "\n" +"Nombre: " + var2 + "\n" +"Categoria: " + var2, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }
    );
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
  public void onClick(View view) {
    recibirJson();
  }

    }
