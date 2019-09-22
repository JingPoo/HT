package com.example.ht;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class hisAsk extends AppCompatActivity {
    float x1 = 0, x2 = 0, y1 = 0, y2 = 0;
    String userId = "";
    String proKey = "";
    int num = 0;
    private ListView listView;
    private ListAdapter listAdapter;
    List<HashMap<String, String>> hashList;
    //private ArrayList<String> list = new ArrayList<String>();

    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    //private DatabaseReference proRef = FirebaseDatabase.getInstance().getReference("problem");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_ask);

        //抓userId
        Intent it = getIntent();
        userId = it.getStringExtra("UserId");

       /* 想丟userID給下面
        Intent intent2 = new Intent();
        intent2.setClass(hisAsk.this,hisAsk.class);
        intent2.putExtra("UserId", userId);
        startActivity(intent2);
        */
        //System.out.println("Here is userID(hisAsk):"+userId);



        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    int i = 0;

                    //從當前user下抓problem總數
                    num = (int)(dataSnapshot.child("user").child(userId).child("problem").getChildrenCount());
                    System.out.println("This is num:"+num);


                            String[] name = new String[num];
                            String[] title = new String[num];
                            String[] content = new String[num];
                            String[] problemid = new String[num];

                            hashList = new ArrayList();

                            //把所有problem Key抓進來
                            for (DataSnapshot d : dataSnapshot.child("user").child(userId).child("problem").getChildren()) {
                                name[i] = d.getKey();
                                i++;
                            }
                            //Test
                        /*for(int j=0;j<name.length;j++) {
                            System.out.println("This is all key" + name[j]);
                        }*/

                            //用problemKey找title & content
                            for (int j = 0; j < name.length; j++) {
                                proKey = name[j];
                                title[j] = dataSnapshot.child("problem").child(proKey).child("title_problem").getValue(String.class);
                                content[j] = dataSnapshot.child("problem").child(proKey).child("content_problem").getValue(String.class);
                                problemid[j] = dataSnapshot.child("problem").child(proKey).getKey();
                            }

                            //放進HashMap裡
                            for (int h = 0; h < title.length; h++) {
                                //System.out.println(title[h]);
                                //System.out.println(content[h]);

                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("title", title[h]);
                                hashMap.put("content", content[h]);
                                hashMap.put("problemID", problemid[h]);
                                hashList.add(hashMap);
                            }

                            listView = (ListView) findViewById(R.id.listView);

                            //只顯示title (Testing)
                        /*ArrayList<String> titleList = new ArrayList<String>();
                        titleList.addAll( Arrays.asList(title) );
                        System.out.println("Here is titleList"+titleList);
                        listAdapter = new ArrayAdapter<String>(hisAsk.this, android.R.layout.simple_list_item_1, titleList);*/

                            //顯示title & content版本
                            listAdapter = new SimpleAdapter(hisAsk.this, hashList, android.R.layout.simple_list_item_2, new String[]{"title", "content"}, new int[]{android.R.id.text1, android.R.id.text2});
                            listView.setAdapter(listAdapter);
                            listView.setOnItemClickListener(onClickListView);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //simpleAdapter listener
    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(hisAns.this,"點選第 "+(position +1) +" 個 \n內容：", Toast.LENGTH_SHORT).show();
            System.out.println("已點選:"+position);

            Iterator it = hashList.iterator();
        Object o = hashList.get(position);
        String c = (String) ((HashMap) o).get("content");
        String t = (String)((HashMap) o).get("title");
        String p = (String) ((HashMap) o).get("problemID");



        System.out.println(o);
        System.out.println(c);
        System.out.println(t);
        System.out.println(p);

        /*想接上面丟下來的userID
        Intent itt = getIntent();
        userId = itt.getStringExtra("UserID");
        */

        //丟uerid,title,content給hisAns2
        Intent intent = new Intent();
        intent.setClass(hisAsk.this,hisAns2.class);
        intent.putExtra("Content", c);
        intent.putExtra("Title",t);
        intent.putExtra("problemID", p);
        startActivity(intent);




            //0.1.2.3  push 2 ->  0.1.2
            //while(it.hasNext()) {
            /*    for (int a = 0; a <= position; a++) {
                    //System.out.println(it.next());
                    if (a == position) {
                        System.out.println("Here!!");
                    } else {
                        //System.out.println(it.next());
                    }

                }*/
            //}
           /* int a =0;
            while (it.hasNext()) {

                //System.out.println(it.next());
                if(a == position){
                                System.out.println(it.next());
                                return;
                            }
                else{
                    a++;
                }
            }
/*
           /* if(it.hasNext() == false){
                System.out.println("No!");
            }*/
        }

    };

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
        it.putExtra("UserId", userId);
        startActivity(it);
    }

    public void gotohisans(){
        Intent it = new Intent(this, hisAns.class );
        it.putExtra("UserId", userId);
        startActivity(it);
    }


}
