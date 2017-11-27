package com.example.firassaid.firasevents;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    Button add,map,back;
    EditText etNom,etType,etPrix,etLieu,etDuree,etDescription;
    String url = "http://172.16.215.1:18080/ProjetPi-web/GED/Event";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        add = (Button) findViewById(R.id.badd);
        map = (Button) findViewById(R.id.bmap);
        back = (Button) findViewById(R.id.bback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this,MainActivity.class));
            }
        });
        etNom = (EditText) findViewById(R.id.etNom);
        etType = (EditText) findViewById(R.id.etType);
        etPrix = (EditText) findViewById(R.id.etPrix);
        etLieu = (EditText) findViewById(R.id.etLieu);
        if(getIntent().getStringExtra("long")!=null){
            etLieu.setText(getIntent().getStringExtra("long")+","+getIntent().getStringExtra("lat"));
        }
        etDuree = (EditText) findViewById(R.id.etDuree);
        etDescription = (EditText) findViewById(R.id.etDescription);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this,AddMapActivity.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String> map = new HashMap<>();
                map.put("nom",etNom.getText().toString());
                map.put("type",etType.getText().toString());
                map.put("prix",etPrix.getText().toString());
                map.put("lieu",etLieu.getText().toString());
                map.put("duree",etDuree.getText().toString());
                map.put("description",etDescription.getText().toString());
                map.put("date_Debut","1509922800000");

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(map), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Context context = getApplicationContext();
                        CharSequence text = error.getMessage();
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");

                        return headers;
                    }
                };


                RequestQueue queue = Volley.newRequestQueue(AddActivity.this);
                queue.add(request);
            }
        });




    }








}
