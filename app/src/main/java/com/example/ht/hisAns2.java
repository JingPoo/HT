package com.example.ht;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class hisAns2 extends AppCompatActivity {

    String userId = "";
    String Content = "";
    String Title = "";
    String proID = "";

    TextView textView1,textView2,textView3,textView4,textView5,textHotTea2;
    Button button1,button2,button3,button4,button5;
    ImageButton menubutton2;

    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    //private ListView listView;
    //private ListAdapter listAdapter;
    //List<HashMap<String, String>> hashList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_ans2);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);
        textHotTea2 = (TextView)findViewById(R.id.textHotTea2);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        menubutton2 = (ImageButton)findViewById(R.id.menubutton2);

        //抓在hisAsk頁面點的問題
        Intent it = getIntent();
        Title = it.getStringExtra("Title");
        Content = it.getStringExtra("Content");
        proID = it.getStringExtra("problemID");
        //userId = it.getStringExtra("UserID");
        System.out.println("hisAns2 ------ Title:"+Title+", Content:"+Content+", Problem ID:"+proID);

       rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int i = 0;
                    //要考慮若回覆超過5則???
                    String[] replyID = new String[5];
                    //抓到點選問題的replyID們了!!
                    for(DataSnapshot d : dataSnapshot.child("problem").child(proID).child("id_reply").getChildren()) {
                        replyID[i] = d.getKey();
                        System.out.println(replyID[i]);
                        i++;
                    }


                    //接下來就是要用replyID去抓reply的內容進textview
                    if(replyID[0] != null){
                        textView1.setText(dataSnapshot.child("reply").child(replyID[0]).child("content_reply").getValue(String.class) );
                    }
                        else {
                            textView1.setText("尚無回覆");
                            button1.setClickable(false);
                            button1.setBackgroundColor(0x54474E1D);
                            button1.setTextColor(0xA82B2525);
                    }
                    if(replyID[1] != null){
                        textView2.setText(dataSnapshot.child("reply").child(replyID[1]).child("content_reply").getValue(String.class));
                    }
                         else {
                            textView2.setText("尚無回覆");
                            button2.setClickable(false);
                            button2.setBackgroundColor(0x54474E1D);
                            button2.setTextColor(0xA82B2525);
                         }
                    if(replyID[2] != null){
                        textView3.setText(dataSnapshot.child("reply").child(replyID[2]).child("content_reply").getValue(String.class));
                    }
                          else {
                             textView3.setText("尚無回覆");
                             button3.setClickable(false);
                             button3.setBackgroundColor(0x54474E1D);
                             button3.setTextColor(0xA82B2525);
                         }
                    if(replyID[3] != null){
                        textView4.setText(dataSnapshot.child("reply").child(replyID[3]).child("content_reply").getValue(String.class));
                    }
                         else {
                             textView4.setText("尚無回覆");
                             button4.setClickable(false);
                             button4.setBackgroundColor(0x54474E1D);
                             button4.setTextColor(0xA82B2525);
                          }
                    if(replyID[4] != null){
                        textView5.setText(dataSnapshot.child("reply").child(replyID[4]).child("content_reply").getValue(String.class));
                    }
                         else {
                             textView5.setText("尚無回覆");
                             button5.setClickable(false);
                             button5.setBackgroundColor(0x54474E1D);
                             button5.setTextColor(0xA82B2525);
                         }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //送茶
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(hisAns2.this);
                final View popView= inflater.inflate(R.layout.give_tea,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(hisAns2.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("取消",null).setPositiveButton("選擇", null).create();
                dialog.show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(hisAns2.this);
                final View popView= inflater.inflate(R.layout.give_tea,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(hisAns2.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("取消",null).setPositiveButton("選擇", null).create();
                dialog.show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(hisAns2.this);
                final View popView= inflater.inflate(R.layout.give_tea,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(hisAns2.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("取消",null).setPositiveButton("選擇", null).create();
                dialog.show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(hisAns2.this);
                final View popView= inflater.inflate(R.layout.give_tea,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(hisAns2.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("取消",null).setPositiveButton("選擇", null).create();
                dialog.show();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(hisAns2.this);
                final View popView= inflater.inflate(R.layout.give_tea,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(hisAns2.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("取消",null).setPositiveButton("選擇", null).create();
                dialog.show();
            }
        });
       /* hashList = new ArrayList();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Title",Title);
        hashMap.put("Content", Content);
        hashList.add(hashMap);
        listView = (ListView) findViewById(R.id.listView);
        //顯示title & content版本
        listAdapter = new SimpleAdapter(hisAns2.this,hashList, android.R.layout.simple_list_item_2, new String[]{"title", "content"}, new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(listAdapter);
        */
    }

    public void gotohome(View v) {

        finish();
    }

    public void gotomenu(View v) {
        Intent it = new Intent(this, menuActivity.class);
        //it.putExtra("UserId", userId);
        startActivity(it);
    }


}
