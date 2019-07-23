package com.example.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class noticeActivity extends AppCompatActivity {
     TextView hottea,noticetext,notice1,notice2,notice3;
     Button noticebutton,menubutton;
     ImageButton backimagebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        hottea = (TextView) findViewById(R.id.hottea);
        noticetext = (TextView) findViewById(R.id.noticetext);
        notice1 = (TextView) findViewById(R.id.notice1);
        notice2 = (TextView) findViewById(R.id.notice2);
        notice3 = (TextView) findViewById(R.id.notice3);
        noticebutton = (Button)findViewById(R.id.noticebutton);
        menubutton = (Button)findViewById(R.id.menubutton);
        backimagebutton = (ImageButton)findViewById(R.id.backimageButton);

    }

    public void gotohome(View v) {
        finish();
    }

    public void gotomenu(View v) {
        Intent it = new Intent(this, menuActivity.class);


        startActivity(it);
    }
}
