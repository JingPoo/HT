package com.example.ht;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class hisAsk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_ask);
    }

    public void gotohome(View v) {
        /*Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        */
        finish();
    }
}
