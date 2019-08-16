package com.example.ht;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ansActivity extends AppCompatActivity {

    //(Function) 抓出要回覆的問題id
    String proid = "1";
    private DatabaseReference proRef = FirebaseDatabase.getInstance().getReference("problem");
    private DatabaseReference proDeRef = proRef.child(proid);
    private DatabaseReference repRef =FirebaseDatabase.getInstance().getReference("reply");

    public static final String REPID_KEY ="id_reply";
    public static final String PROID_KEY ="id_problem";
    public static final String CONTENT_KEY ="content_reply";
    public static final String TEAID_KEY ="id_tea";
    public static final String TIME_KEY ="time_reply";
    public static final String REPORT_KEY ="been_reported_reply";
    public static final String INAPPRO_KEY ="inappropriate_content_reply";
    public static final String TAG ="ReplyingQuestion";




    TextView hottea,questitle,quescontent;

    ImageButton backimagebutton,sendimagebutton,menubutton;

    LinearLayout linearLayout;
    EditText anstext;
    String[] name = new String[50];
    int ranPick = 0;
    String proKey = "";
    Button reject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ans);

        hottea = (TextView)findViewById(R.id.hottea);
        questitle = (TextView)findViewById(R.id.questitle);
        quescontent = (TextView)findViewById(R.id.quescontent);

        menubutton =  findViewById(R.id.menubutton);

        backimagebutton = (ImageButton)findViewById(R.id.backimageButton);
        sendimagebutton = (ImageButton)findViewById(R.id.sendimageButton);
        anstext = findViewById(R.id.anstext);
        reject = findViewById(R.id.reject);
        reject.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ansActivity.this)
                        .setIcon(R.drawable.logo)
                        .setTitle("我們收到了！謝謝你撥空閱讀這則提問。")
                        .setPositiveButton("關閉", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create()
                        .show();
            }
        });

        new AlertDialog.Builder(ansActivity.this)
                .setIcon(R.drawable.logo)
                .setTitle("貼心小提醒")
                .setMessage("在大家熱心回答前~要請大家注意一下用詞內容!\n" +
                        "像是避免使用\n" +
                        "·罵人自找 等等明顯攻擊性話語\n" +
                        "·激問句帶來的貶低和否定等等帶有隱含意思的語句\n" +
                        "·強人哲學：你就要努力等等 （批評你不夠努力⋯⋯)\n" +
                        "·歧視同志、跨性別等等觀點\n" +
                        "真誠地回應才能溫暖並幫助需要的人!!\n")
                .setNegativeButton("我知道了",null).create()
                .show();

       /* //set Title
        String title = "Title";
        questitle.setText(title);

        //set Content
        String content = "Content";
        quescontent.setText(content);
        */

        proRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                if (dataSnapshot.exists()) {
                    int i = 0;
                    for(DataSnapshot d : dataSnapshot.getChildren()) {
                        name[i] = d.getKey();
                        i++;
                    }
                    for(int j=0;j<50;j++) {
                        System.out.println("This is all key" + name[j]);
                    }

                    //10可以改成抓線上problem的數量count
                    ranPick = (int)(Math.random()*10);

                    //System.out.println("This is ranPick:"+ranPick);
                    proKey = name[ranPick];
                    String title = dataSnapshot.child(proKey).child("title_problem").getValue(String.class);
                    questitle.setText(title);
                    //System.out.println("This is title:"+title);
                    String content = dataSnapshot.child(proKey).child("content_problem").getValue(String.class);
                    quescontent.setText(content);
                    //System.out.println("This is content:"+content);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void gotohome(View v) {
        Intent it = new Intent(this, mainPageActivity.class);

        startActivity(it);
    }

    public void gotomenu(View v) {
        Intent it = new Intent(this, menuActivity.class);

        startActivity(it);
    }

    public void returnAns(View v){

        //Time
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sendTime = new Date(System.currentTimeMillis());
        String repTime = formatter.format(sendTime);

        //Been Reported (Function)
        Boolean reported = false;

        //Inappropriate Content (Function)
        Boolean inappro = false;

        // TeaID (Function)
        String teaid = "001";

       //隨機產生id_reply
        repRef.push();

        String repid = repRef.push().getKey();

        String ans = anstext.getText().toString();
        anstext.setText("");

        if (ans.isEmpty() ) { return; }
        Map<String, Object> dataToSave = new HashMap<String, Object>();

        dataToSave.put(CONTENT_KEY,ans);
        dataToSave.put(PROID_KEY,proid);
        dataToSave.put(TIME_KEY,repTime);
        dataToSave.put(REPORT_KEY,reported);
        dataToSave.put(INAPPRO_KEY,inappro);
        dataToSave.put(TEAID_KEY,teaid);

        repRef.child(repid).setValue(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG,"Document has been saved!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG,"Document was not saved!",e);
            }
        });

        //testing
        System.out.println(ans);


    }
}
