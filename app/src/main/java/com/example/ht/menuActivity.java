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
     Button teahousebutton,dictbutton,settingbutton;
     Switch leaveswitch;
     String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        closeimagebutton = (ImageButton)findViewById(R.id.closeimageButton);
        teahousebutton = (Button)findViewById(R.id.teahousebutton);
        dictbutton = (Button)findViewById(R.id.dictbutton);
        settingbutton = (Button)findViewById(R.id.settingbutton);
        leaveswitch = (Switch)findViewById(R.id.leaveswitch);

        Intent it = getIntent();
        userId = it.getStringExtra("UserId");
        //Toast.makeText(this, "Here is userId:"+userId, Toast.LENGTH_SHORT).show();
        System.out.println("Here is userID(menuActivity):"+userId);
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
