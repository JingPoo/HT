package com.example.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class signupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    //去登入頁的功能
    public void gotoSignIn(View v) {
        Intent it = new Intent(this, MainActivity.class);

        startActivity(it);
    }
}
