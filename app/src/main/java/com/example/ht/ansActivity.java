package com.example.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ansActivity extends AppCompatActivity {
    TextView hottea,questitle,quescontent;
    Button noticebutton,menubutton;
    ImageButton backimagebutton,sendimagebutton;
    LinearLayout linearLayout;
    EditText anstext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ans);

        hottea = (TextView)findViewById(R.id.hottea);
        questitle = (TextView)findViewById(R.id.questitle);
        quescontent = (TextView)findViewById(R.id.quescontent);
        noticebutton = (Button)findViewById(R.id.noticebutton);
        menubutton = (Button)findViewById(R.id.menubutton);
        backimagebutton = (ImageButton)findViewById(R.id.backimageButton);
        sendimagebutton = (ImageButton)findViewById(R.id.sendimageButton);
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        anstext = (EditText)findViewById(R.id.anstext);

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

    public void deleteText(View v){
        anstext.setText("");
    }
}
