package com.example.ht;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class menuActivity extends AppCompatActivity {

    private DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("user");

     ImageButton closeimagebutton;
     Button teahousebutton;
     Switch leaveswitch;
     String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        closeimagebutton = (ImageButton)findViewById(R.id.closeimageButton);
        teahousebutton = (Button)findViewById(R.id.teahousebutton);
        leaveswitch = (Switch)findViewById(R.id.leaveswitch);

        Intent it = getIntent();
        userId = it.getStringExtra("UserId");
        //Toast.makeText(this, "Here is userId:"+userId, Toast.LENGTH_SHORT).show();
        System.out.println("Here is userID(menuActivity):"+userId);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(userId).child("receive_problem").getValue(String.class).equals("true")){
                    leaveswitch.setChecked(false);
                }
                else{
                    leaveswitch.setChecked(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        leaveswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    userRef.child(userId).child("receive_problem").setValue("false");
                    System.out.println(userId+"請假中");
                }
                else {
                    userRef.child(userId).child("receive_problem").setValue("true");
                    System.out.println(userId+"無請假");
                }
            }
        });
    }

    public void gototearoom(View v) {
        Intent it = new Intent(this, TeaRoomActivity.class );
        it.putExtra("UserId", userId);
        startActivity(it);
    }
    public void gotohelp(View v) {
        Intent it = new Intent(this, helpGPSActivity.class );
        startActivity(it);
    }
    public void gotoextra(View v) {
        Intent it = new Intent(this, helpGPSActivity.class );
        startActivity(it);
    }
    public void gotohome(View v) {
        Intent it = new Intent(this, mainPageActivity.class );
        it.putExtra("UserId", userId);
        startActivity(it);
    }

}
