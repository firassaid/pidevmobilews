package com.example.firassaid.firasevents;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView events;
    Button add;
    Context context = this;
    List<Event> listevents = new ArrayList<>();
    ArrayAdapter listAdapter;
    List<String> eventNames = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        events = (ListView) findViewById(R.id.events);
        add = (Button) findViewById(R.id.badd);
        getEvents();
      /*  arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                eventNames );
        events.setAdapter(listAdapter);*/

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this,AddActivity.class));
            }
        });


        events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String ss =events.getItemAtPosition(i).toString();
                String[] parts = ss.split(":");

                String s = parts[1].trim();
                s = s.replaceAll("\\D+","");
              //  Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                Intent in = new Intent(MainActivity.this,DeleteActivity.class);
                in.putExtra("id",s);
                String m = parts[4].replaceAll("\\s+","");
                in.putExtra("map",m);
               // Toast.makeText(context, m, Toast.LENGTH_SHORT).show();
                startActivity(in);


            }
        });
    }





    public void getEvents() {


        String url =  "http://172.16.215.1:18080/ProjetPi-web/GED/Event";


        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response", response);

                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                        //googleMap.addMarker(new MarkerOptions().position(location).title("Current N°:666").icon(BitmapDescriptorFactory.fromResource(R.mipmap.postmarkerg))).showInfoWindow();

                        try {

                            JSONArray jarray = new JSONArray(response);

                            for (int i = 0; i < jarray.length(); i++) {

                                JSONObject j = jarray.getJSONObject(i);
                                Event e = new Event(j);

                                listevents.add(e);
                            //    Toast.makeText(context, e.getNom(), Toast.LENGTH_SHORT).show();


                            }
                            for (Event e : listevents) {
                                eventNames.add("id: "+e.getId()+ "\nNom: "+e.getNom()+"\nType: "+e.getType()+"\nLieu: "+e.getLieu());
                            }
                           // Toast.makeText(context, "names"+eventNames.size(), Toast.LENGTH_SHORT).show();

                            listAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, eventNames);


                            events.setAdapter(listAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error setting note : " + error.getMessage());
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                if (error instanceof TimeoutError) {
                    System.out.println(error.toString());
                }


            }
        }));
    }


}
