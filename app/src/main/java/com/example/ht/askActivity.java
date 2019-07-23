package com.example.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class askActivity extends AppCompatActivity {
    TextView hottea;
    Button noticebutton,menubutton,sendbutton;
    ImageButton backimageButton;
    EditText questitle,quescontent;
    Spinner type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        hottea = (TextView)findViewById(R.id.hottea);
        noticebutton = (Button)findViewById(R.id.noticebutton);
        menubutton = (Button)findViewById(R.id.menubutton);
        sendbutton = (Button)findViewById(R.id.sendbutton);
        backimageButton = (ImageButton) findViewById(R.id.backimageButton);
        questitle = (EditText)findViewById(R.id.questitle);
        quescontent = (EditText)findViewById(R.id.quescontent);
        type = (Spinner)findViewById(R.id.type);

    }

    public void gotohome(View v) {
        finish();
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
