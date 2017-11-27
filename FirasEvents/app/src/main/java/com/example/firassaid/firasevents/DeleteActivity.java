package com.example.firassaid.firasevents;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DeleteActivity extends AppCompatActivity {

    Button yes,no,map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        yes =(Button) findViewById(R.id.bdelete);
        no =(Button) findViewById(R.id.bcancel);
        map =(Button) findViewById(R.id.bmap);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(DeleteActivity.this,ViewActivity.class);
                in.putExtra("map",getIntent().getStringExtra("map"));
                startActivity(in);
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cancelEvent(getIntent().getStringExtra("id"));

            }
        });
    }



    public void cancelEvent(String id) {



        JSONObject j=new JSONObject();
        try {
            j.put("name","anything");
        } catch (JSONException e) {

            e.printStackTrace();
        }
        StringRequest js=new StringRequest(Request.Method.DELETE, "http://172.16.215.1:18080/ProjetPi-web/GED/Event/"+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(DeleteActivity.this, response, Toast.LENGTH_SHORT).show();
                Intent i=new Intent(DeleteActivity.this,MainActivity.class);
                startActivity(i);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map=new HashMap<String, String>();
                map.put("Content-type","application/json; charset=utf-8");
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(js);



    }
}
