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
 * Created by Lenovo Lap on 7/22/2017.
 */

class BackgroundWorker2 extends AsyncTask<String,Void,String> {


    Context context;
    String amountf = "";
    String name = "";
    String JSON_STRING = "";
    String rag1 = "";
    String username1 = "";
    String amountt="";
    BackgroundWorker2(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        String login_url = "http://172.29.32.73/raghav3.php";
            try {
                username1 = params[0];
                amountf= params[2];
                name = params[1];
                amountt = params[3];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username1", "UTF-8") + "=" + URLEncoder.encode(username1, "UTF-8")+"&"+
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")+"&"+
                        URLEncoder.encode("amountf", "UTF-8") + "=" + URLEncoder.encode(amountf, "UTF-8")+"&"+
                        URLEncoder.encode("amountt", "UTF-8") + "=" + URLEncoder.encode(amountt, "UTF-8");
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
        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    public void onPostExecute(String result) {

            Intent i = new Intent(context, Display1.class);
            i.putExtra("epuzzle2",result );
            context.startActivity(i);

    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
