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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import static java.text.DateFormat.getDateTimeInstance;

public class askActivity extends AppCompatActivity {

    //private DatabaseReference databaseReference;
    private DatabaseReference proRef = FirebaseDatabase.getInstance().getReference("problem");
    private DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("user");


    public static final String TITLE_KEY ="title_problem";
    public static final String CONTENT_KEY ="content_problem";
    public static final String CATEGORY_KEY ="category_problem";
    public static final String TIME_KEY ="time_problem";
    public static final String STAGE_KEY ="stage";
    public static final String FROM_KEY ="from";
    //public static final String PROID_KEY ="id_problem";
    public static final String REPORT_KEY ="been_reported_problem";
    public static final String INAPPRO_KEY ="inappropriate_content_problem";
    public static final String TAG ="AskingQuestion";


    TextView hottea;
    Button noticebutton,sendbutton;
    ImageButton backimageButton,menubutton;
    EditText questitle,quescontent;
    Spinner type;

    String userId = "";


    //髒話列表(要更新兩邊都要更新)   ----->未來用Function做同步
    ArrayList <String> a = new ArrayList(Arrays.asList("幹","靠","機掰","你娘","屎","乳頭","雞雞","雞掰","雞巴","雞八","王八","哭邀","哭腰","怪胎","腦殘","白癡","北七","媽的","低能","智障","屁眼","陰道","陰莖","去死","腦殘","喜憨"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        hottea = (TextView)findViewById(R.id.hottea);
        menubutton = findViewById(R.id.menubutton);
        sendbutton = (Button)findViewById(R.id.sendbutton);
        backimageButton = (ImageButton) findViewById(R.id.backimageButton);
        questitle = (EditText)findViewById(R.id.questitle);
        quescontent = (EditText)findViewById(R.id.quescontent);
        type = (Spinner)findViewById(R.id.type);


        new AlertDialog.Builder(askActivity.this)
                .setIcon(R.drawable.logo)
                .setTitle("貼心小提醒")
                .setMessage("嗨!今天的你過得怎麼樣呢?\n" +
                        "在跟別人聊聊之前，不妨先看看自己的狀況!\n" +
                        "記得別人的意見並不是絕對的。\n" +
                        "你才是能替自己作主的人!\n" +
                        "嘗試著走出吧!跟父母朋友聊聊，\n" +
                        "或是尋求諮商師的幫助更有用喔!\n"
                        )
                .setNegativeButton("我知道了",null).create()
                .show();


        Intent it = getIntent();
        userId = it.getStringExtra("UserId");
        //Toast.makeText(this, "Here is userId:"+userId, Toast.LENGTH_SHORT).show();
        System.out.println("Here is userID(askActivity):"+userId);

    System.out.println("髒話列表:"+a);



    }

    public void gotohome(View v) {
        finish();
    }

    /*public void gotonotice(View v) {
        Intent it = new Intent(this, noticeActivity.class);
        it.putExtra("UserId", userId);
        startActivity(it);
    }*/

    public void gotomenu(View v) {
        Intent it = new Intent(this, menuActivity.class);
        it.putExtra("UserId", userId);
        startActivity(it);
    }



    public void sendAsk(View v){

        //產生獨一無二 id_problem
        proRef.push();

        // ProblemId (Function)
        final String proid= proRef.push().getKey();



        //Title
        String askTitle = questitle.getText().toString();

        //Type
        int askTypenum = type.getSelectedItemPosition();
        String askType = "";

        switch (askTypenum){
            case 0:
                askType = "感情";
                break;
            case 1:
                askType = "家庭";
                break;
            case 2:
                askType = "課業";
                break;
            case 3:
                askType = "未來";
                break;
            case 4:
                askType = "生活";
                break;
        }

        //Content
        String askCont = quescontent.getText().toString();

        //Time
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sendTime = new Date(System.currentTimeMillis());
        String askTime = formatter.format(sendTime);

        //Stage
        int stage = 1;


        //Been Reported (Function)
        Boolean reported = false;

        //Inappropriate Content (Function)
        Boolean inappro = false;

        //Testing
        System.out.println(askTitle);
        System.out.println(askType);
        System.out.println(askCont);
        System.out.println(askTime);
        System.out.println(stage);

        //是否有含髒話
        boolean contain = false;
        //是否有欄位為空
        if (askTitle.isEmpty() || askCont.isEmpty()){
            Toast.makeText(this, "標題或內容有缺漏，請再檢查一次", Toast.LENGTH_SHORT).show();
            contain = true;
        }
        for (int i=0;i<a.size();i++) {
            //-1是沒有髒話
            if (askTitle.indexOf(a.get(i)) != -1 || askCont.indexOf(a.get(i)) != -1) {
                Toast.makeText(this, "標題或內容有髒話，請再檢查一次", Toast.LENGTH_SHORT).show();
                contain = true;
                System.out.println("Here is the bad:"+askTitle+"+"+askCont);
            }
        }

        //都符合，才寫入db
            if(contain == false) {
                Map<String, Object> dataToSave = new HashMap<String, Object>();
                dataToSave.put(TITLE_KEY, askTitle);
                dataToSave.put(CONTENT_KEY, askCont);
                dataToSave.put(CATEGORY_KEY, askType);
                dataToSave.put(TIME_KEY, ServerValue.TIMESTAMP);
                dataToSave.put(STAGE_KEY, stage);
                dataToSave.put(FROM_KEY,userId);
                dataToSave.put(INAPPRO_KEY, reported);
                dataToSave.put(REPORT_KEY, inappro);
                proRef.child(proid).setValue(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Document has been saved!");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Document was not saved!", e);
                    }
                });
              //  Map<String, Object> dataToSave2 = new HashMap<String, Object>();
              //  dataToSave2.put(proid,askTitle);
                if(userId.isEmpty() == false) {
                    userRef.child(userId).child("problem").child(proid).setValue(askTitle);
                }

                questitle.setText("");
                quescontent.setText("");
                type.setSelection(0);
                Toast.makeText(this, "已送出! 靜待回覆", Toast.LENGTH_SHORT).show();
                //sendbutton.setText("已送出! 靜待回覆");

                //看能不能加個彈跳視窗說已送出
            }

        }




    }
