package com.lenovolap.loginrag;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Transfer extends AppCompatActivity {
    String password1="";
    String username1="";
    TextView p3name,name1;
    String rag="";
    String rag1="";
    String rag5="";
    String rag6="";
    String rag7="";

    View view1;

    AlertDialog alertDialog;
    EditText amount,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        Ainf(view1);
        amount = (EditText)findViewById(R.id.amount4);
        name = (EditText)findViewById(R.id.name4);
    }
    public void Ainf(View view)
    {
        p3name=(TextView)findViewById(R.id.p3name);
        name1=(TextView)findViewById(R.id.name1);
        Intent extras = getIntent();
        username1 = extras.getStringExtra("epuzzle");
        password1 = extras.getStringExtra("epuzzle1");
        rag = extras.getStringExtra("epuzzle2");

        try {
            JSONObject object = new JSONObject(rag);
            JSONArray jArray3 = object.getJSONArray("server_response");
            for(int i = 0; i < jArray3 .length(); i++)
            {
                JSONObject object3 = jArray3.getJSONObject(i);
                rag1 = object3.getString("name");
                username1= object3.getString("username");
                password1 = object3.getString("password");
                rag5 = object3.getString("amount");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        p3name.setText(rag1);
        name1.setText(rag5);

    }
    public void confirm(View view)
    {
        rag7 = amount.getText().toString();
        rag6 = name.getText().toString();
        String type = "login";
        int a = Integer.parseInt(rag5);
        int b = Integer.parseInt(rag7);
        if(a>=b)
        {
            int d=a-b;
            rag5=String.valueOf(d);
            BackgroundWorker1 backgroundWorker = new BackgroundWorker1(this);
            backgroundWorker.execute(type, username1, password1, rag7, rag6, rag5);
        }
       else
        {
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Status");
            alertDialog.setMessage("Insufficient amount");
            alertDialog.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    alertDialog.dismiss();
                }
            }, 10000);
        }
    }
}
