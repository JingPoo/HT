package com.example.ht;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

//起始網頁:參考https://chuancode.com/android-studio-splash-screen/
public class splash extends AppCompatActivity {

    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.logo);

        Animation myanim = AnimationUtils.loadAnimation(splash.this, R.anim.fadein);

        logo.startAnimation(myanim);

        final Intent aftersplash = new Intent(splash.this,MainActivity.class);

        Thread timer = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(aftersplash);
                    finish();
                }
            }
        };

        timer.start();
    }
}
