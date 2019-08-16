package com.example.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;

public class menuActivity extends AppCompatActivity {
     ImageButton closeimagebutton;
     Button teahousebutton;
     Switch leaveswitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        closeimagebutton = (ImageButton)findViewById(R.id.closeimageButton);
        teahousebutton = (Button)findViewById(R.id.teahousebutton);
        leaveswitch = (Switch)findViewById(R.id.leaveswitch);
    }

    public void gototearoom(View v) {
        Intent it = new Intent(this, TeaRoomActivity.class );
        startActivity(it);
    }
    public void gotohelp(View v) {
        Intent it = new Intent(this, helpGPSActivity.class );
        startActivity(it);
    }
    public void gotohome(View v) {
        finish();
    }

}
