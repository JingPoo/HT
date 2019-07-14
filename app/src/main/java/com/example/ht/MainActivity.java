package com.example.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView hottea;
    Button askbutton,ansbutton,noticebutton,menubutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hottea = (TextView)findViewById(R.id.hottea);
        askbutton = (Button)findViewById(R.id.askbutton);
        ansbutton= (Button)findViewById(R.id.ansbutton);
        noticebutton = (Button)findViewById(R.id.noticebutton);
        menubutton = (Button)findViewById(R.id.menubutton);
    }

    public void gotoask(View v) {
        Intent it = new Intent(this, askActivity.class);

        startActivity(it);
    }
    public void gotoans(View v) {
        Intent it = new Intent(this, ansActivity.class);

        startActivity(it);
    }
    public void gotonotice(View v) {
        Intent it = new Intent(this, noticeActivity.class);

        startActivity(it);
    }
    public void gotomenu(View v) {
        Intent it = new Intent(this, menuActivity.class);

        startActivity(it);
    }
}
