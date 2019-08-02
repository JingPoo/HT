package com.example.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class mainPageActivity extends AppCompatActivity {

    TextView hottea;
    ImageButton askbutton,ansbutton,noticebutton,menubutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        hottea = (TextView)findViewById(R.id.textHotTea);
        askbutton = (ImageButton)findViewById(R.id.asknowButton);
        ansbutton= (ImageButton)findViewById(R.id.ansnowButton);
        noticebutton = (ImageButton)findViewById(R.id.inboxButton);
        //menubutton = (ImageButton)findViewById(R.id.menubutton); 目前用不到，打算加入滑動頁面
    }

    //去發問頁得功能
    public void gotoask(View v) {
        Intent it = new Intent(this, askActivity.class);

        startActivity(it);
    }

    //去最近未回答的問題的頁面的功能
    public void gotoans(View v) {
        Intent it = new Intent(this, ansActivity.class);

        startActivity(it);
    }

    //前往通知頁的功能
    public void gotonotice(View v) {
        Intent it = new Intent(this, noticeActivity.class);

        startActivity(it);
    }


    /*將加入滑動頁面，暫時無需使用
    public void gotohisask(){
        Intent it = new Intent(this, hisAsk.class );
        startActivity(it);
    }

    public void gotomenu(View v) {
        Intent it = new Intent(this, menuActivity.class);


        startActivity(it);
    }*/
}
