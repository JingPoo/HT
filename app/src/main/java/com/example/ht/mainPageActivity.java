package com.example.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class mainPageActivity extends AppCompatActivity {

    TextView hottea;
    ImageButton askbutton,ansbutton,noticebutton,menubutton;
    float x1 = 0, x2 = 0, y1 = 0, y2 = 0;

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


    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            //手指按下
            x1 = event.getX();
            x2 = event.getY();
        }

        if(event.getAction() == MotionEvent.ACTION_UP){
            //手指離開
            x2 = event.getX();
            y2 = event.getY();

            if(x1 - x2 > 50){  //左滑
                gotohisask();
            }
            else if(x1 - x2 < 50){
                gotomenu();
            }

        }
        return true;
    }


    public void gotohisask(){
        Intent it = new Intent(this, hisAsk.class );
        startActivity(it);
    }

    public void gotomenu() {
        Intent it = new Intent(this, menuActivity.class);
        startActivity(it);
    }
}
