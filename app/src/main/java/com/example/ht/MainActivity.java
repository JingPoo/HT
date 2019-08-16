package com.example.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//新增
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    //TextView hottea;
    //Button askbutton,ansbutton,noticebutton,menubutton;

    //float x1 = 0, x2 = 0, y1 = 0, y2 = 0;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String signinuserText;
    private String signinpassText;
    private EditText signinuserTextEdit;
    private EditText signinpassTextEdit;
    private ImageButton gomainpageButton;
    private FirebaseUser user;




    private FirebaseAnalytics mFirebaseAnalytics;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mAuth = FirebaseAuth.getInstance();
        signinuserTextEdit = (EditText) findViewById(R.id.signinuserText);
        signinpassTextEdit = (EditText) findViewById(R.id.signinpassText);
        gomainpageButton = (ImageButton) findViewById(R.id.gomainpageButton);



        gomainpageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = signinuserTextEdit.getText().toString();
                String password = signinpassTextEdit.getText().toString();
                if(TextUtils.isEmpty(account)){

                    return;
                }
                if(TextUtils.isEmpty(password)){

                    return;
                }

                mAuth.signInWithEmailAndPassword(account, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String userId = signinuserTextEdit.getText().toString().replace('.','a');
                                    Toast.makeText(MainActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent();
                                    intent.setClass(MainActivity.this, mainPageActivity.class);
                                    intent.putExtra("UserId", userId);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

        /*hottea = (TextView)findViewById(R.id.hottea);
        askbutton = (Button)findViewById(R.id.askbutton);
        ansbutton= (Button)findViewById(R.id.ansbutton);
        noticebutton = (Button)findViewById(R.id.noticebutton);
        menubutton = (Button)findViewById(R.id.menubutton);*/

    //signinuserText = (EditText)findViewById(R.id.signinuserText);

    // Obtain the FirebaseAnalytics instance.
    //mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

    //databaseReference = FirebaseDatabase.getInstance().getReference();
    //FirebaseDatabase mdb = FirebaseDatabase.getInstance();
    //DatabaseReference mRef = mdb.getReference();
    //DatabaseReference mDataRed = mRef.child("user");
    // FirebaseDatabase.getInstance().getReference().child("user").setValue("HandsomeJing");


    //去主頁的功能

    //public void gotoMainPage(View v) {
        //String userId = signinuserText.getText().toString().replace('.','a');

    /*public void gotoMainPage(View v) {

        String userId = signinuserText.getText().toString();
        //String userId = "123";
        Intent it = new Intent(this, mainPageActivity.class);
        it.putExtra("UserId", userId);
        startActivity(it);
    }*/

    //去註冊頁的功能
    public void gotoSignUp(View v) {
        Intent it = new Intent(this, signupActivity.class);

        startActivity(it);
    }

}
