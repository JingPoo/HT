package com.example.ht;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class sendTeaActivity extends AppCompatActivity {

    String replyID ="";
    String replyer = "";
    int bainum;
    int asanum;
    int greennum;
    int fournum;
    int funum;
    int fenum;
    int milknum;
    int goldnum;
    int poonum;
    ImageButton bai,asa,green,four,fu,fe,milk,gold,poo;
    TextView baiT,asaT,greenT,fourT,fuT,feT,milkT,goldT,pooT;

    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_tea);

        Intent it = getIntent();
        replyID = it.getStringExtra("replyID");
        System.out.println("Reply ID:"+replyID);

        bai = (ImageButton)findViewById(R.id.bai);
        asa = (ImageButton)findViewById(R.id.asa);
        green = (ImageButton)findViewById(R.id.green);
        four = (ImageButton)findViewById(R.id.four);
        fu = (ImageButton)findViewById(R.id.fu);
        fe = (ImageButton)findViewById(R.id.fe);
        milk = (ImageButton)findViewById(R.id.milk);
        gold = (ImageButton)findViewById(R.id.gold);
        poo = (ImageButton)findViewById(R.id.poo);
        baiT = (TextView)findViewById(R.id.baiT);
        asaT = (TextView)findViewById(R.id.asaT);
        greenT = (TextView)findViewById(R.id.greenT);
        fourT = (TextView)findViewById(R.id.fourT);
        fuT = (TextView)findViewById(R.id.fuT);
        feT = (TextView)findViewById(R.id.feT);
        milkT = (TextView)findViewById(R.id.milkT);
        goldT = (TextView)findViewById(R.id.goldT);
        pooT = (TextView)findViewById(R.id.pooT);

        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    replyer = dataSnapshot.child("reply").child(replyID).child("from").getValue(String.class);
                    bainum = dataSnapshot.child("user").child(replyer).child("teabook").child("bai").getValue(Integer.class);
                    asanum = dataSnapshot.child("user").child(replyer).child("teabook").child("asa").getValue(Integer.class);
                    greennum = dataSnapshot.child("user").child(replyer).child("teabook").child("green").getValue(Integer.class);
                    fournum = dataSnapshot.child("user").child(replyer).child("teabook").child("four").getValue(Integer.class);
                    funum = dataSnapshot.child("user").child(replyer).child("teabook").child("fu").getValue(Integer.class);
                    fenum = dataSnapshot.child("user").child(replyer).child("teabook").child("fe").getValue(Integer.class);
                    milknum = dataSnapshot.child("user").child(replyer).child("teabook").child("milk").getValue(Integer.class);
                    goldnum = dataSnapshot.child("user").child(replyer).child("teabook").child("gold").getValue(Integer.class);
                    poonum = dataSnapshot.child("user").child(replyer).child("teabook").child("poo").getValue(Integer.class);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootRef.child("reply").child(replyID).child("id_tea").setValue("bai");
                System.out.println("bai");
                rootRef.child("user").child(replyer).child("teabook").child("bai").setValue(bainum+1);
                finish();
            }
        });
        asa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootRef.child("reply").child(replyID).child("id_tea").setValue("asa");
                System.out.println("asa");
                rootRef.child("user").child(replyer).child("teabook").child("asa").setValue(asanum+1);
                finish();
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootRef.child("reply").child(replyID).child("id_tea").setValue("green");
                System.out.println("green");
                rootRef.child("user").child(replyer).child("teabook").child("green").setValue(greennum+1);
                finish();
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootRef.child("reply").child(replyID).child("id_tea").setValue("four");
                System.out.println("four");
                rootRef.child("user").child(replyer).child("teabook").child("four").setValue(fournum+1);
                finish();
            }
        });
        fu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootRef.child("reply").child(replyID).child("id_tea").setValue("fu");
                System.out.println("fu");
                rootRef.child("user").child(replyer).child("teabook").child("fu").setValue(funum+1);
                finish();
            }
        });
        fe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootRef.child("reply").child(replyID).child("id_tea").setValue("fe");
                System.out.println("fe");
                rootRef.child("user").child(replyer).child("teabook").child("fe").setValue(fenum+1);
                finish();
            }
        });
        milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootRef.child("reply").child(replyID).child("id_tea").setValue("milk");
                System.out.println("milk");
                rootRef.child("user").child(replyer).child("teabook").child("milk").setValue(milknum+1);
                finish();
            }
        });
        gold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootRef.child("reply").child(replyID).child("id_tea").setValue("gold");
                System.out.println("gold");
                rootRef.child("user").child(replyer).child("teabook").child("gold").setValue(goldnum+1);
                finish();
            }
        });
        poo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootRef.child("reply").child(replyID).child("id_tea").setValue("poo");
                System.out.println("poo");
                rootRef.child("user").child(replyer).child("teabook").child("poo").setValue(poonum+1);
                finish();
            }
        });

    }

}
