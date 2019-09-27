package com.example.ht;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeaRoomActivity extends AppCompatActivity {

    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    String userId = "";
    ImageButton bai, green, asa, fe, poo, four, gold, fu, milk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_room);

        //抓userId
        Intent it = getIntent();
        userId = it.getStringExtra("UserId");
        System.out.println("Here is userID(TeaRoom):"+userId);
        /*if( dataSnapshot.child("user").child(userId).child("teabook").child("bai").child("collected").getValue() == "true") {

            final int amount = Integer.parseInt(dataSnapshot.child("user").child(userId).child("teabook").child("bai").child("amount").getKey());
            */


        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    final Long baiAmount = (Long)dataSnapshot.child("user").child(userId).child("teabook").child("bai").getValue();
                    final Long greenAmount = (Long)dataSnapshot.child("user").child(userId).child("teabook").child("green").getValue();
                    final Long asaAmount = (Long)dataSnapshot.child("user").child(userId).child("teabook").child("asa").getValue();
                    final Long feAmount = (Long)dataSnapshot.child("user").child(userId).child("teabook").child("fe").getValue();
                    final Long pooAmount = (Long)dataSnapshot.child("user").child(userId).child("teabook").child("poo").getValue();
                    final Long fourAmount = (Long)dataSnapshot.child("user").child(userId).child("teabook").child("four").getValue();
                    final Long goldAmount = (Long)dataSnapshot.child("user").child(userId).child("teabook").child("gold").getValue();
                    final Long fuAmount = (Long)dataSnapshot.child("user").child(userId).child("teabook").child("fu").getValue();
                    final Long milkAmount = (Long)dataSnapshot.child("user").child(userId).child("teabook").child("milk").getValue();

                    //九個說明的彈跳視窗
                         //白毫彈跳視窗
                         bai = findViewById(R.id.bai);
                         bai.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View v) {
                                 if(baiAmount > 0) {
                                     LayoutInflater inflater = LayoutInflater.from(TeaRoomActivity.this);
                                     final View popView = inflater.inflate(R.layout.bai, null);
                                     final AlertDialog.Builder builder = new AlertDialog.Builder(TeaRoomActivity.this);
                                     builder.setView(popView);
                                     final AlertDialog dialog = builder.setNegativeButton("關閉", null).create();
                                     dialog.show();
                                     Toast.makeText(TeaRoomActivity.this, "您已收集了"+baiAmount+"個此茶葉!", Toast.LENGTH_SHORT).show();
                                 }
                                 if(baiAmount == 0) {
                                     Toast.makeText(TeaRoomActivity.this, "您尚未收集此茶葉", Toast.LENGTH_SHORT).show();
                                 }
                             }
                         });



                         //抹茶彈跳視窗
                         green = findViewById(R.id.green);
                         green.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View v) {
                                 if(greenAmount > 0) {
                                     LayoutInflater inflater = LayoutInflater.from(TeaRoomActivity.this);
                                     final View popView = inflater.inflate(R.layout.greenpop, null);
                                     final AlertDialog.Builder builder = new AlertDialog.Builder(TeaRoomActivity.this);
                                     builder.setView(popView);
                                     final AlertDialog dialog = builder.setNegativeButton("關閉", null).create();
                                     dialog.show();
                                     Toast.makeText(TeaRoomActivity.this, "您已收集了"+greenAmount+"個此茶葉!", Toast.LENGTH_SHORT).show();
                                 }
                                 if(greenAmount == 0){
                                     Toast.makeText(TeaRoomActivity.this,"您尚未收集此茶葉",Toast.LENGTH_SHORT).show();
                                     System.out.println("您尚未收集此茶葉");
                                 }
                             }
                         });

                    //阿薩姆紅茶彈跳視窗
                    asa = findViewById(R.id.asa);
                    asa.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(asaAmount > 0) {
                                LayoutInflater inflater = LayoutInflater.from(TeaRoomActivity.this);
                                final View popView = inflater.inflate(R.layout.asapop, null);
                                final AlertDialog.Builder builder = new AlertDialog.Builder(TeaRoomActivity.this);
                                builder.setView(popView);
                                final AlertDialog dialog = builder.setNegativeButton("關閉", null).create();
                                dialog.show();
                                Toast.makeText(TeaRoomActivity.this, "您已收集了"+asaAmount+"個此茶葉!", Toast.LENGTH_SHORT).show();
                            }
                            if(asaAmount == 0){
                                Toast.makeText(TeaRoomActivity.this,"您尚未收集此茶葉",Toast.LENGTH_SHORT).show();
                                System.out.println("您尚未收集此茶葉");
                            }
                        }
                    });

                    //鐵觀音彈跳視窗
                    fe = findViewById(R.id.fe);
                    fe.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(feAmount > 0) {
                                LayoutInflater inflater = LayoutInflater.from(TeaRoomActivity.this);
                                final View popView = inflater.inflate(R.layout.fepop, null);
                                final AlertDialog.Builder builder = new AlertDialog.Builder(TeaRoomActivity.this);
                                builder.setView(popView);
                                final AlertDialog dialog = builder.setNegativeButton("關閉", null).create();
                                dialog.show();
                                Toast.makeText(TeaRoomActivity.this, "您已收集了"+feAmount+"個此茶葉!", Toast.LENGTH_SHORT).show();
                            }
                            if(feAmount == 0){
                                Toast.makeText(TeaRoomActivity.this,"您尚未收集此茶葉",Toast.LENGTH_SHORT).show();
                                System.out.println("您尚未收集此茶葉");
                            }
                        }
                    });

                    //普爾彈跳視窗
                    poo = findViewById(R.id.poo);
                    poo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(pooAmount > 0) {
                                LayoutInflater inflater = LayoutInflater.from(TeaRoomActivity.this);
                                final View popView = inflater.inflate(R.layout.poopop, null);
                                final AlertDialog.Builder builder = new AlertDialog.Builder(TeaRoomActivity.this);
                                builder.setView(popView);
                                final AlertDialog dialog = builder.setNegativeButton("關閉", null).create();
                                dialog.show();
                                Toast.makeText(TeaRoomActivity.this, "您已收集了"+pooAmount+"個此茶葉!", Toast.LENGTH_SHORT).show();
                            }
                            if(pooAmount == 0){
                                Toast.makeText(TeaRoomActivity.this,"您尚未收集此茶葉",Toast.LENGTH_SHORT).show();
                                System.out.println("您尚未收集此茶葉");
                            }
                        }
                    });

                    //四季春彈跳視窗
                    four = findViewById(R.id.four);
                    four.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(fourAmount > 0) {
                                LayoutInflater inflater = LayoutInflater.from(TeaRoomActivity.this);
                                final View popView = inflater.inflate(R.layout.fourpop, null);
                                final AlertDialog.Builder builder = new AlertDialog.Builder(TeaRoomActivity.this);
                                builder.setView(popView);
                                final AlertDialog dialog = builder.setNegativeButton("關閉", null).create();
                                dialog.show();
                                Toast.makeText(TeaRoomActivity.this, "您已收集了"+fourAmount+"個此茶葉!", Toast.LENGTH_SHORT).show();
                            }
                            if(fourAmount == 0){
                                Toast.makeText(TeaRoomActivity.this,"您尚未收集此茶葉",Toast.LENGTH_SHORT).show();
                                System.out.println("您尚未收集此茶葉");
                            }
                        }
                    });

                    //金萱彈跳視窗
                    gold = findViewById(R.id.gold);
                    gold.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(goldAmount > 0) {
                                LayoutInflater inflater = LayoutInflater.from(TeaRoomActivity.this);
                                final View popView = inflater.inflate(R.layout.goldpop, null);
                                final AlertDialog.Builder builder = new AlertDialog.Builder(TeaRoomActivity.this);
                                builder.setView(popView);
                                final AlertDialog dialog = builder.setNegativeButton("關閉", null).create();
                                dialog.show();
                                Toast.makeText(TeaRoomActivity.this, "您已收集了"+goldAmount+"個此茶葉!", Toast.LENGTH_SHORT).show();
                            }
                            if(goldAmount == 0){
                                Toast.makeText(TeaRoomActivity.this,"您尚未收集此茶葉",Toast.LENGTH_SHORT).show();
                                System.out.println("您尚未收集此茶葉");
                            }
                        }
                    });

                    //佛手茶彈跳視窗
                    fu = findViewById(R.id.fu);
                    fu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(fuAmount > 0) {
                                LayoutInflater inflater = LayoutInflater.from(TeaRoomActivity.this);
                                final View popView = inflater.inflate(R.layout.fupop, null);
                                final AlertDialog.Builder builder = new AlertDialog.Builder(TeaRoomActivity.this);
                                builder.setView(popView);
                                final AlertDialog dialog = builder.setNegativeButton("關閉", null).create();
                                dialog.show();
                                Toast.makeText(TeaRoomActivity.this, "您已收集了"+fuAmount+"個此茶葉!", Toast.LENGTH_SHORT).show();
                            }
                            if(fuAmount == 0){
                                Toast.makeText(TeaRoomActivity.this,"您尚未收集此茶葉",Toast.LENGTH_SHORT).show();
                                System.out.println("您尚未收集此茶葉");
                            }
                        }
                    });

                    //奶茶彈跳視窗
                    milk = findViewById(R.id.milk);
                    milk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (milkAmount > 0) {
                                LayoutInflater inflater = LayoutInflater.from(TeaRoomActivity.this);
                                final View popView = inflater.inflate(R.layout.milkpop, null);
                                final AlertDialog.Builder builder = new AlertDialog.Builder(TeaRoomActivity.this);
                                builder.setView(popView);
                                final AlertDialog dialog = builder.setNegativeButton("關閉", null).create();
                                dialog.show();
                                Toast.makeText(TeaRoomActivity.this, "您已收集了"+milkAmount+"個此茶葉!", Toast.LENGTH_SHORT).show();
                            }
                            if(milkAmount == 0){
                                Toast.makeText(TeaRoomActivity.this,"您尚未收集此茶葉",Toast.LENGTH_SHORT).show();
                                System.out.println("您尚未收集此茶葉");
                            }
                        }
                    });




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });









            }







    public void gotomenu(View v) {
        Intent it = new Intent(this, menuActivity.class);

        startActivity(it);
    }
}
