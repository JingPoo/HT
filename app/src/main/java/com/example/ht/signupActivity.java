package com.example.ht;

import android.content.Intent;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;

public class signupActivity extends AppCompatActivity {

    //當下使用者id
     String userid;

    private DatabaseReference signupRef = FirebaseDatabase.getInstance().getReference("user");

    public static final String NAME_KEY ="real_name";
    public static final String PHONE_KEY ="cellphone_number";
    public static final String EMAIL_KEY ="email";
    public static final String ACCUNT_KEY ="id_account";
    public static final String PASSWORD_KEY ="password";
    public static final String RECIEVEPRO_KEY ="receive_problem";
    public static final String REPORTEDTIME_KEY ="reported_times";
    public static final String TAG ="SigningUp";


    TextView signuptextView;
    EditText nameeditText,emaileditText,phoneeditText,usereditText,psweditText1,psweditText2,notsignedupeditText;
    ImageButton enterButton;
    Button signinButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signuptextView = (TextView)findViewById(R.id.signuptextView);
        nameeditText = (EditText)findViewById(R.id.nameeditText);
        emaileditText = (EditText)findViewById(R.id.emaileditText);
        phoneeditText = (EditText)findViewById(R.id.phoneeditText);
        usereditText = (EditText)findViewById(R.id.usereditText);
        psweditText1 = (EditText)findViewById(R.id.psweditText1);
        psweditText2 = (EditText)findViewById(R.id.psweditText2);
        notsignedupeditText = (EditText)findViewById(R.id.notsignedupeditText);
        enterButton = (ImageButton)findViewById(R.id.enterButton);
        signinButton = (Button)findViewById(R.id.signinbutton);



    }

    public void backtosignin(View v){
        finish();
    }

    //去登入頁的功能
    public void gotoSignIn(View v) {

        //UserName
        String username = nameeditText.getText().toString();
        //UserEmail
        String useremail = emaileditText.getText().toString();
        //UserPhone -------> 限定手機長度?
        String userphone = phoneeditText.getText().toString();
        //UserID (使用者帳號) -------> 須確保不能重複!!!!!(待解決)
        userid = usereditText.getText().toString();
        //UserPassword -------> 限制不能太簡單?
        String userpsw = psweditText1.getText().toString();
        //再輸入一次UserPassword  -------> 比對密碼12(待解決)
        String userpswcomfirm = psweditText2.getText().toString();

        //限制都有做到才跳到登入(待解決)
        // 密碼不一致，清空輸入欄2
        //跳出提示請使用者必填欄位



        //是否接收問題(請假)
        Boolean receivepro = true;

        //被檢舉次數
        int reportedtime = 0;

        if(userid.equals("") || username.equals("") || userphone.equals("") || useremail.equals("") || userpsw.equals("") || userpswcomfirm.equals("")){
            Toast.makeText(this, "資料不完整，填妥後請再試一次", Toast.LENGTH_SHORT).show();
        }
        else {
            //產生獨一無二 id_user
            signupRef.child(userid);

            Map<String, Object> dataToSave = new HashMap<String, Object>();
            dataToSave.put(NAME_KEY, username);
            dataToSave.put(PHONE_KEY, userphone);
            dataToSave.put(EMAIL_KEY, useremail);
            dataToSave.put(PASSWORD_KEY, userpsw);
            dataToSave.put(RECIEVEPRO_KEY, receivepro);
            dataToSave.put(REPORTEDTIME_KEY, reportedtime);
            signupRef.child(userid).setValue(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
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

            Intent it = new Intent(this, MainActivity.class);

            startActivity(it);
        }
    }
}
