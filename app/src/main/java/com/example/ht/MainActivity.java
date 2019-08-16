package com.example.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    //TextView hottea;
    //Button askbutton,ansbutton,noticebutton,menubutton;

    //float x1 = 0, x2 = 0, y1 = 0, y2 = 0;
    EditText signinuserText;

    private FirebaseAnalytics mFirebaseAnalytics;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*hottea = (TextView)findViewById(R.id.hottea);
        askbutton = (Button)findViewById(R.id.askbutton);
        ansbutton= (Button)findViewById(R.id.ansbutton);
        noticebutton = (Button)findViewById(R.id.noticebutton);
        menubutton = (Button)findViewById(R.id.menubutton);*/
        signinuserText = (EditText)findViewById(R.id.signinuserText);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase mdb = FirebaseDatabase.getInstance();
        DatabaseReference mRef = mdb.getReference();
        DatabaseReference mDataRed = mRef.child("user");
       // FirebaseDatabase.getInstance().getReference().child("user").setValue("HandsomeJing");
    }

    //去主頁的功能
    public void gotoMainPage(View v) {
        //String userId = signinuserText.getText().toString().replace('.','a');
        String userId = signinuserText.getText().toString();
        //String userId = "123";
        Intent it = new Intent(this, mainPageActivity.class);
        it.putExtra("UserId", userId);
        startActivity(it);
    }

    //去註冊頁的功能
    public void gotoSignUp(View v) {
        Intent it = new Intent(this, signupActivity.class);

        startActivity(it);
    }

}
