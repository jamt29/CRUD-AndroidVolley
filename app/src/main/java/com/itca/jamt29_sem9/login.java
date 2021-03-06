package com.itca.jamt29_sem9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class login extends AppCompatActivity {
    private Button btn_login, olvidatsecontra;
    private EditText et_correo, et_contra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        olvidatsecontra = findViewById(R.id.olvidastecontra);
        et_contra = findViewById(R.id.et_contra);
        et_correo = findViewById(R.id.et_correo);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_login(login.this, et_correo.getText().toString(), et_contra.getText().toString());
            }
        });
    }

    public void registrate(View view) {
    }




    public void btn_login(final Context context, final String user, final String pass) {
        String url="https://mjgl.com.sv/ws_2021/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals(0)) {
                    Toast.makeText(context, "" + response, Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONObject respuestaJSON = new JSONObject(response.toString());
                        String id = respuestaJSON.getString("id");
                        String nombre = respuestaJSON.getString("nombre");
                        String apellidos = respuestaJSON.getString("apellidos");
                        String correo = respuestaJSON.getString("correo");
                        String usuario = respuestaJSON.getString("usuario");
                        String clave = respuestaJSON.getString("clave");
                        String tipo = respuestaJSON.getString("tipo");
                        String estado = respuestaJSON.getString("estado");
                        String pregunta = respuestaJSON.getString("pregunta");
                        String respuesta = respuestaJSON.getString("respuesta");
                        String fecha_registro = respuestaJSON.getString("fecha_registro");

                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(login.this, MainActivity.class);
                        startActivity(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error. Problemas en la conexion al servidor", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected HashMap<String, String> getParams(){
                HashMap<String, String> parametos = new HashMap<>();
                parametos.put("usu", user.trim());
                parametos.put("pas", pass.trim());
                return parametos;

            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public void olvidastecontra(View view) {
    }
}