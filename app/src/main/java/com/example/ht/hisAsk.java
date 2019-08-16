package com.example.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class hisAsk extends AppCompatActivity {
    float x1 = 0, x2 = 0, y1 = 0, y2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_ask);
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

            if(x1 - x2 < 50){
                finish();
            }
            else if(x1 - x2 > 50){
                gotohisans();
            }

        }
        return true;
    }

    public void gotohome(View v) {

        finish();
    }

    public void gotomenu(View v) {
        Intent it = new Intent(this, menuActivity.class);

        startActivity(it);
    }

    public void gotohisans(){
        Intent it = new Intent(this, hisAns.class );
        startActivity(it);
    }


}
