package com.example.ht;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import maes.tech.intentanim.CustomIntent;

public class mainPageActivity extends AppCompatActivity {

    private DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("user");

    TextView hottea;

    ImageButton askbutton,ansbutton,menubutton;


    String userId = "";

    Button teach;

    float x1 = 0, x2 = 0, y1 = 0, y2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        hottea = (TextView)findViewById(R.id.textHotTea);
        askbutton = (ImageButton)findViewById(R.id.asknowButton);
        ansbutton= (ImageButton)findViewById(R.id.ansnowButton);
        teach = findViewById(R.id.teachButton);
        menubutton = (ImageButton)findViewById(R.id.menubutton);

        Intent it = getIntent();
        userId = it.getStringExtra("UserId");
        //Toast.makeText(this, "Here is userId:"+userId, Toast.LENGTH_SHORT).show();
        System.out.println("Here is userID(MainPage):"+userId);

        //askbutton.setOnDragListener();
        //教學彈出視窗:進入最近回答
        teach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(mainPageActivity.this);
                final View popView= inflater.inflate(R.layout.teach,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(mainPageActivity.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("取消",null).create();
                dialog.show();
            }
        });

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("State:"+dataSnapshot.child(userId).child("receive_problem").getValue());
                if (dataSnapshot.child(userId).child("receive_problem").getValue(String.class).equals("false")){
                    ansbutton.setEnabled(false);
                }
                else if(dataSnapshot.child(userId).child("receive_problem").getValue(String.class).equals("true")){
                    System.out.println("true");
                }
                else{
                    System.out.println("nothing");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

    //去發問頁得功能
    public void gotoask(View v) {
        Intent it = new Intent(this, askActivity.class);
        it.putExtra("UserId", userId);
        startActivity(it);
    }

    //去最近未回答的問題的頁面的功能
    public void gotoans(View v) {
        Intent it = new Intent(this, ansActivity.class);
        it.putExtra("UserId", userId);
        startActivity(it);
    }

    //前往通知頁的功能
    /*public void gotonotice(View v) {
        Intent it = new Intent(this, noticeActivity.class);
        it.putExtra("UserId", userId);
        startActivity(it);
    }*/



    public void gotohisask(){
        Intent it = new Intent(this, hisAsk.class );
        it.putExtra("UserId", userId);
        startActivity(it);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void gotomenu(View v) {
        Intent it = new Intent(this, menuActivity.class);
        it.putExtra("UserId", userId);
        startActivity(it);
    }
}
