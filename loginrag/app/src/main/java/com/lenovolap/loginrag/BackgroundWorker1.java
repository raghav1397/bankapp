package com.lenovolap.loginrag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;

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

/**
 * Created by Lenovo Lap on 7/21/2017.
 */

class BackgroundWorker1 extends AsyncTask<String,Void,String> {

    Activity activity;
    Context context;
    AlertDialog alertDialog;
    int a = 0;
    String JSON_STRING = "";
    String rag1 = "";
    String username1 = "";
    String password1 = "";
    String amount="";
    BackgroundWorker1(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://172.29.32.73/raghav2.php";
        if (type.equals("login")) {
            try {
                String user_name = params[1];
                String password = params[2];
                amount= params[3];
                String name = params[4];
                String amountf = params[5];
                rag1=amountf;
                username1 = user_name;
                password1 = password;
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") +"&"+
                        URLEncoder.encode("amount", "UTF-8") + "=" + URLEncoder.encode(amount, "UTF-8")+"&"+
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")+"&"+
                        URLEncoder.encode("amountf", "UTF-8") + "=" + URLEncoder.encode(amountf, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                String raghav = stringBuilder.toString().trim();
                return raghav;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
    }

    @Override
    public void onPostExecute(String result) {

        String abi1="Transaction Successful";

        String aa = result;
        if(aa.equals("Invalid To Account")){
            alertDialog.setMessage(result);
            alertDialog.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    alertDialog.dismiss();
                }
            }, 10000);

        }else{

            Intent i = new Intent(context, Display2.class);
            i.putExtra("epuzzle",username1 );
            i.putExtra("epuzzle3",rag1);
            i.putExtra("epuzzle4",amount );
            i.putExtra("epuzzle1",password1 );
            i.putExtra("epuzzle2",result );
            context.startActivity(i);
        }

    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}