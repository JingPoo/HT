package com.example.ht;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class hisAns2 extends AppCompatActivity {

    String userId = "";
    String Content = "";
    String proID = "";
    String Title = "";


    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    private ListView listView;
    private ListAdapter listAdapter;
    List<HashMap<String, String>> hashList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_ans2);

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
                    String[] replyID = new String[2];
                    //抓到點選問題的replyID們了!!
                    for(DataSnapshot d : dataSnapshot.child("problem").child(proID).child("id_reply").getChildren()) {
                        replyID[i] = d.getKey();
                        System.out.println(replyID[i]);
                     
                        i++;

                        //接下來就是要用replyID去抓reply的內容進LISTVIEW

                    }
                   // String replyText = dataSnapshot.child("reply").child(replyID).child("content_reply").getValue(String.class);

                    //System.out.println("These are replies to this question "+replyText);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
