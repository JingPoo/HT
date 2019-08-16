package com.example.ht;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class hisAns extends AppCompatActivity {
    float x1 = 0, x2 = 0, y1 = 0, y2 = 0;
    String userId = "";
    String proKey = "";
    String repKey = "";
    int num = 0;
    private ListView listView;
    private ListAdapter listAdapter;

    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_ans);

        Intent it = getIntent();
        userId = it.getStringExtra("UserId");
        //System.out.println("Here is userID(hisAns):"+userId);

        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    int i = 0;
                    int k = 0;

                    //從當前user下抓reply總數
                    num = (int)(dataSnapshot.child("user").child(userId).child("reply").getChildrenCount());
                    System.out.println("This is num:"+num);

                    String[] replyname = new String[num];
                    String[] proname = new String[num];
                    String[] title = new String[num];
                    String[] content = new String[num];
                    List<HashMap<String, String>> hashList = new ArrayList();

                    //把所有reply Key抓進來
                    for(DataSnapshot d : dataSnapshot.child("user").child(userId).child("reply").getChildren()) {
                        replyname[i] = d.getKey();
                        i++;
                    }
                    //Test
                    /*for(int j=0;j<name.length;j++) {
                        System.out.println("This is all key" + name[j]);
                    }*/

                    i = 0;
                    for(i=0;i<replyname.length;i++) {
                        //System.out.println("problemID:"+a.getValue().toString());
                        proname[k] = dataSnapshot.child("reply").child(replyname[i]).child("id_problem").getValue(String.class);
                        //System.out.println("problemID:"+proname[k]);
                        k++;

                     }


                    for(int j=0;j<num;j++) {
                        System.out.println("This is reply+problem key" + replyname[j]+"+"+proname[j]);
                    }

                    //用problemKey,replyKey找title & content
                    for(int j=0;j<replyname.length;j++) {
                        proKey = proname[j];
                        repKey = replyname[j];
                        title[j] = dataSnapshot.child("problem").child(proKey).child("title_problem").getValue(String.class);
                        content[j] = dataSnapshot.child("reply").child(repKey).child("content_reply").getValue(String.class);
                    }

                    //放進HashMap裡
                    for(int h=0;h<title.length;h++){
                        //System.out.println(title[h]);
                        //System.out.println(content[h]);

                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("title", title[h]);
                        hashMap.put("content", content[h]);
                        hashList.add(hashMap);
                    }

                    listView = (ListView) findViewById(R.id.listView);

                    //只顯示title (Testing)
                    /*ArrayList<String> titleList = new ArrayList<String>();
                    titleList.addAll( Arrays.asList(title) );
                    System.out.println("Here is titleList"+titleList);
                    listAdapter = new ArrayAdapter<String>(hisAsk.this, android.R.layout.simple_list_item_1, titleList);*/

                    //顯示title & content版本
                    listAdapter = new SimpleAdapter(hisAns.this,hashList, android.R.layout.simple_list_item_2, new String[]{"title", "content"}, new int[]{android.R.id.text1, android.R.id.text2});
                    listView.setAdapter(listAdapter);


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

            if(x1 - x2 < 50){
                finish();
            }

        }
        return true;
    }

    public void gotohome(View v) {

        finish();
    }

    /*public void gotonotice(View v) {
        Intent it = new Intent(this, noticeActivity.class);
        it.putExtra("UserId", userId);
        startActivity(it);
    }*/

    public void gotomenu(View v) {
        Intent it = new Intent(this, menuActivity.class);
        it.putExtra("UserId", userId);
        startActivity(it);
    }
}
