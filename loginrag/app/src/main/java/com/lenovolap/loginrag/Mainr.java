package com.lenovolap.loginrag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Mainr extends AppCompatActivity {
    String password1="";
    String username1="";
    String rag="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainr);
        Intent extras = getIntent();
      username1 = extras.getStringExtra("epuzzle");
        password1 = extras.getStringExtra("epuzzle1");
        rag = extras.getStringExtra("epuzzle2");

    }

    public void Ainf(View view)
    {
        Intent i = new Intent(this, Transfer.class);
        i.putExtra("epuzzle",username1 );
        i.putExtra("epuzzle1",password1 );
        i.putExtra("epuzzle2",rag );
        startActivity(i);
    }
    public void Ainfo(View view)
    {
        Intent i = new Intent(this, Acount_info.class);
        i.putExtra("epuzzle",username1 );
        i.putExtra("epuzzle1",password1 );
        i.putExtra("epuzzle2",rag );
        startActivity(i);
    }
    public void Onlogout(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


}
