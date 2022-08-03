package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;
public class  MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText usuario = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText contraseña = (EditText) findViewById(R.id.editTextTextPassword);
        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarWs(usuario.getText().toString(),contraseña.getText().toString());

            }
        });
    }

    private void enviarWs(String u, String c) {

        String url = "https://testandroid.macropay.com.mx";
        ImageView qr=findViewById(R.id.qri);
        StringRequest postResquest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject api_response = new JSONObject(response);
                    String message = api_response.getString("token");

                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    i.putExtra("token", message);
                    startActivity(i);
                } catch (JSONException  e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", u);
                params.put("password", c);

                return params;
            }
        };
        Volley.newRequestQueue(this).add(postResquest);
    }
}