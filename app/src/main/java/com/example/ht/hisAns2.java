package com.example.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class hisAns2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_ans2);
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
