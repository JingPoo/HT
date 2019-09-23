package com.example.ht;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class realhisAns2 extends AppCompatActivity {

    String userId = "";
    String Content = "";
    String Title = "";
    String proID = "";
    String repID = "";

    TextView textView1,textView2,textView3,teatextView,textHotTea3,QtextView,RtextView;
    ImageButton menubutton3;

    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realhis_ans2);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        teatextView = (TextView)findViewById(R.id.teatextView);
        textHotTea3 = (TextView)findViewById(R.id.textHotTea3);
        RtextView = (TextView)findViewById(R.id.RtextView);
        QtextView = (TextView)findViewById(R.id.QtextView);
        menubutton3 = (ImageButton)findViewById(R.id.menubutton3);

        //抓在hisAns頁面點的回覆
        Intent it = getIntent();
        Title = it.getStringExtra("Title");
        Content = it.getStringExtra("Content");
        proID = it.getStringExtra("problemID");
        repID = it.getStringExtra("replyID");
        //userId = it.getStringExtra("UserID");
        System.out.println("hisAns2 ------ Title:"+Title+", Content:"+Content+", Problem ID:"+proID+", Reply ID:"+repID);

        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String Qtitle = Title;
                    String Qcontent = dataSnapshot.child("problem").child(proID).child("content_problem").getValue(String.class);
                    String Rcontent = Content;

                    textView1.setText(Qtitle);
                    textView2.setText(Qcontent);
                    textView3.setText(Rcontent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



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
