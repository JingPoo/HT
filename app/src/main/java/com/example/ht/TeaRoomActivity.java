package com.example.ht;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TeaRoomActivity extends AppCompatActivity {

    ImageButton bai, green, asa, fe, poo, four, gold, fu, milk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_room);

        //九個說明的彈跳視窗
        //白毫彈跳視窗
        bai = findViewById(R.id.bai);
        bai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(TeaRoomActivity.this);
                final View popView= inflater.inflate(R.layout.bai,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(TeaRoomActivity.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("關閉",null).create();
                dialog.show();
            }
        });

        //抹茶彈跳視窗
        green = findViewById(R.id.green);
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(TeaRoomActivity.this);
                final View popView= inflater.inflate(R.layout.greenpop,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(TeaRoomActivity.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("關閉",null).create();
                dialog.show();
            }
        });

        //阿薩姆紅茶彈跳視窗
        asa = findViewById(R.id.asa);
        asa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(TeaRoomActivity.this);
                final View popView= inflater.inflate(R.layout.asapop,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(TeaRoomActivity.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("關閉",null).create();
                dialog.show();
            }
        });

        //鐵觀音彈跳視窗
        fe = findViewById(R.id.fe);
        fe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(TeaRoomActivity.this);
                final View popView= inflater.inflate(R.layout.fepop,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(TeaRoomActivity.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("關閉",null).create();
                dialog.show();
            }
        });

        //普爾彈跳視窗
        poo = findViewById(R.id.poo);
        poo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(TeaRoomActivity.this);
                final View popView= inflater.inflate(R.layout.poopop,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(TeaRoomActivity.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("關閉",null).create();
                dialog.show();
            }
        });

        //四季春彈跳視窗
        four = findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(TeaRoomActivity.this);
                final View popView= inflater.inflate(R.layout.fourpop,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(TeaRoomActivity.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("關閉",null).create();
                dialog.show();
            }
        });

        //金萱彈跳視窗
        gold = findViewById(R.id.gold);
        gold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(TeaRoomActivity.this);
                final View popView= inflater.inflate(R.layout.goldpop,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(TeaRoomActivity.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("關閉",null).create();
                dialog.show();
            }
        });

        //佛手茶彈跳視窗
        fu = findViewById(R.id.fu);
        fu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(TeaRoomActivity.this);
                final View popView= inflater.inflate(R.layout.fupop,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(TeaRoomActivity.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("關閉",null).create();
                dialog.show();
            }
        });

        //奶茶彈跳視窗
        milk = findViewById(R.id.milk);
        milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(TeaRoomActivity.this);
                final View popView= inflater.inflate(R.layout.milkpop,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(TeaRoomActivity.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("關閉",null).create();
                dialog.show();
            }
        });
    }


    public void gotomenu(View v) {
        Intent it = new Intent(this, menuActivity.class);

        startActivity(it);
    }
}
