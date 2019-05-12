package com.lenovolap.loginrag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Raghav extends AppCompatActivity {

    String password1="";
    String username1="";
    TextView p1name,p2name,p3name,name1;
    String rag="";
    String rag1="";
    String rag2="";
    String rag3="";
    String rag4="";
    String rag5="";

    View view1;
    String JSON_STRING="";
    TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raghav);
        Ainfo(view1);

    }

    public void Ainf(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void Ainfo(View view)
    {
        textViewResult=(TextView)findViewById(R.id.textViewResult);
        p1name=(TextView)findViewById(R.id.p1name);
        p2name=(TextView)findViewById(R.id.p2name);
        p3name=(TextView)findViewById(R.id.p3name);
        name1=(TextView)findViewById(R.id.name1);
        Bundle extras = getIntent().getExtras();
        username1 = extras.getString("epuzzle");
        password1 = extras.getString("epuzzle1");
        rag = extras.getString("epuzzle2");

        try {
            JSONObject object = new JSONObject(rag);
            JSONArray jArray3 = object.getJSONArray("server_response");
            for(int i = 0; i < jArray3 .length(); i++)
            {
                JSONObject object3 = jArray3.getJSONObject(i);
                rag1 = object3.getString("name");
                rag2 = object3.getString("surname");
                rag3 = object3.getString("age");
                rag4 = object3.getString("username");
                rag5 = object3.getString("amount");


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        textViewResult.setText(rag1);
        p1name.setText(rag2);
        p2name.setText(rag3);
        p3name.setText(rag4);
        name1.setText(rag5);
    }
}
