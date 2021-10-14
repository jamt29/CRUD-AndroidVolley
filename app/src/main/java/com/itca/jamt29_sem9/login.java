package com.itca.jamt29_sem9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
    }

    public void registrate(View view) {
    }

    public void btn_login(final Context context, final String user, final String pass) {
        String url="https://mjgl.com.sv/ws_2021/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "" + response, Toast.LENGTH_SHORT).show();
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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
    }

    public void olvidastecontra(View view) {
    }
}