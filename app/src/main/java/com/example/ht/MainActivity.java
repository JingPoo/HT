package com.example.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextView hottea;
    Button askbutton,ansbutton,noticebutton,menubutton;

    float x1 = 0, x2 = 0, y1 = 0, y2 = 0;

    private FirebaseAnalytics mFirebaseAnalytics;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hottea = (TextView)findViewById(R.id.hottea);
        askbutton = (Button)findViewById(R.id.askbutton);
        ansbutton= (Button)findViewById(R.id.ansbutton);
        noticebutton = (Button)findViewById(R.id.noticebutton);
        menubutton = (Button)findViewById(R.id.menubutton);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase mdb = FirebaseDatabase.getInstance();
        DatabaseReference mRef = mdb.getReference();
        DatabaseReference mDataRed = mRef.child("user");
        FirebaseDatabase.getInstance().getReference().child("user").setValue("Eileen");
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

        }
        return true;
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


    public void gotohisask(){
        Intent it = new Intent(this, hisAsk.class );
        startActivity(it);
    }

    public void gotomenu(View v) {
        Intent it = new Intent(this, menuActivity.class);


        startActivity(it);
    }
}
