package com.lenovolap.loginrag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;


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
import java.util.Arrays;
import java.util.List;



public class Display2 extends AppCompatActivity {
    String password1 = "";
    String username1 = "";
    String rag = "";
    String rag1 = "";
    String rag2 = "";
    String rag3 = "";
    String rag4 = "";
    String rag5 = "";
    String rag6="";
    String rag9 = "";
    String rag8 = "";
    String rag10 = "";
    int randomNum=0;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2);
        confirm1();

    }

    private void getValue()
    {
        BackgroundWorker3 backgroundWorker = new BackgroundWorker3(this);
        backgroundWorker.execute(username1, password1);
    }
    private void processValue(String myValue)
    {
        rag=myValue;
        try {
            JSONObject object = new JSONObject(rag);
            JSONArray jArray3 = object.getJSONArray("server_response");
            for(int i = 0; i < jArray3 .length(); i++)
            {
                JSONObject object3 = jArray3.getJSONObject(i);
                rag6=object3.getString("email");

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        onClick();
    }
    public void confirm1() {
        Bundle extras = getIntent().getExtras();
        username1 = extras.getString("epuzzle");
        password1 = extras.getString("epuzzle1");
        rag8 = extras.getString("epuzzle3");
        rag = extras.getString("epuzzle2");
        rag9 = extras.getString("epuzzle4");
        try {
            JSONObject object = new JSONObject(rag);
            JSONArray jArray3 = object.getJSONArray("server_response");
            for (int i = 0; i < jArray3.length(); i++) {
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

        getValue();
    }
    public void confirm2(View v)
    {       EditText otp;
            otp = (EditText)findViewById(R.id.editText);
            String otp1 = otp.getText().toString();
            int otp2=Integer.parseInt(otp1);
        if(otp2==randomNum)
        {
            int a = Integer.parseInt(rag5);
            int b = Integer.parseInt(rag9);
            int d = a + b;
            rag10 = String.valueOf(d);
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Status");
            alertDialog.setMessage("Transaction Successful");
            alertDialog.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    alertDialog.dismiss();
                }
            }, 10000);
            BackgroundWorker2 backgroundWorker = new BackgroundWorker2(this);
            backgroundWorker.execute(username1, rag4, rag8, rag10);
        }
        else
        {
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Status");
            alertDialog.setMessage("Invalid otp");
            alertDialog.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    alertDialog.dismiss();
                }
            }, 10000);
        }

    }

public void send(View v)
{
    getValue();
}
public void menu(View v)
{
    Intent i = new Intent(this, Mainr.class);
    startActivity(i);
}
private class BackgroundWorker3  extends AsyncTask<String,Void,String> {


    Context context;


    BackgroundWorker3(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = null;
        String register_url = "http://172.29.32.73/sagar.php";

        try {
            String username = params[0];
            String password = params[1];
            URL url = new URL(register_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                    + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            StringBuilder builder = new StringBuilder();

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line+"\n");
            }
            result = builder.toString().trim();
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    public void onPostExecute(String result) {

        processValue(result);
    }

}



    public void onClick() {
        Log.i("SendMailActivity", "Send Button Clicked.");

        String fromEmail = "raghavendhar1371997007@gmail.com";
        String fromPassword = "Abirami137";
        String toEmails = rag6;
        List<String> toEmailList = Arrays.asList(toEmails
                .split("\\s*,\\s*"));
        Log.i("SendMailActivity", "To List: " + toEmailList);
        String emailSubject = "OTP";
        randomNum = 1000 + (int)(Math.random() * 8999);
        String str1 = String.valueOf(randomNum);
        String emailBody = str1;
        new SendMailTask(Display2.this).execute(fromEmail,
                fromPassword, toEmailList, emailSubject, emailBody);
    }

}