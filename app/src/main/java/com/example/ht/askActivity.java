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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.String;

public class askActivity extends AppCompatActivity {
    TextView hottea;
    Button noticebutton,sendbutton;
    ImageButton backimageButton,menubutton;
    EditText questitle,quescontent;
    Spinner type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        hottea = (TextView)findViewById(R.id.hottea);
        menubutton = findViewById(R.id.menubutton);
        sendbutton = (Button)findViewById(R.id.sendbutton);
        backimageButton = (ImageButton) findViewById(R.id.backimageButton);
        questitle = (EditText)findViewById(R.id.questitle);
        quescontent = (EditText)findViewById(R.id.quescontent);
        type = (Spinner)findViewById(R.id.type);

    }

    public void gotohome(View v) {
        finish();
    }

    public void gotomenu(View v) {
        Intent it = new Intent(this, menuActivity.class);

        startActivity(it);
    }

    public void sendAsk(View v){
        //Title
        String askTitle = questitle.getText().toString();

        //Type
        int askTypenum = type.getSelectedItemPosition();
        String askType = "";

        switch (askTypenum){
            case 0:
                askType = "感情";
                break;
            case 1:
                askType = "家庭";
                break;
            case 2:
                askType = "課業";
                break;
            case 3:
                askType = "未來";
                break;
            case 4:
                askType = "生活";
                break;
        }

        //Content
        String askCont = quescontent.getText().toString();

        //Time
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sendTime = new Date(System.currentTimeMillis());
        String askTime = formatter.format(sendTime);

        //Stage
        int stage = 1;

        //Testing
        System.out.println(askTitle);
        System.out.println(askType);
        System.out.println(askCont);
        System.out.println(askTime);
        System.out.println(stage);
    }

}
