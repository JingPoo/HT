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

//新加的
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String emaileditText;
    private String psweditText1;
    private String psweditText2;
    private EditText emaileditTextEdit;
    private EditText psweditText1Edit;
    private EditText psweditText2Edit;
    private ImageButton enterButton;
    private Button signinbutton;
    private FirebaseUser user;

    private String nameeditText;
    private String phoneeditText;
    private String usereditText;
    private EditText nameeditTextEdit;
    private EditText phoneeditTextEdit;
    private EditText usereditTextEdit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();
    }

    private void initView() {
        mAuth = FirebaseAuth.getInstance();
        emaileditTextEdit = (EditText) findViewById(R.id.emaileditText);
        psweditText1Edit = (EditText) findViewById(R.id.psweditText1);
        psweditText2Edit = (EditText) findViewById(R.id.psweditText2);
        enterButton = (ImageButton) findViewById(R.id.enterButton);

        nameeditTextEdit = (EditText) findViewById(R.id.nameeditText);
        phoneeditTextEdit = (EditText) findViewById(R.id.phoneeditText);
        usereditTextEdit = (EditText) findViewById(R.id.usereditText);



        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emaileditText = emaileditTextEdit.getText().toString();
                String psweditText1 = psweditText1Edit.getText().toString();
                String psweditText2 = psweditText2Edit.getText().toString();

                String nameeditText = nameeditTextEdit.getText().toString();
                String phoneeditText = phoneeditTextEdit.getText().toString();
                String usereditText = usereditTextEdit.getText().toString();

                if(TextUtils.isEmpty(nameeditText)){
                    Toast.makeText(signupActivity.this, "請填入姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(emaileditText)){
                    Toast.makeText(signupActivity.this, "請填入email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phoneeditText)){
                    Toast.makeText(signupActivity.this, "請填入電話號碼", Toast.LENGTH_SHORT).show();
                    return;
                }if(TextUtils.isEmpty(usereditText)){
                    Toast.makeText(signupActivity.this, "請輸入帳號", Toast.LENGTH_SHORT).show();
                    return;
                }if(TextUtils.isEmpty(psweditText1)){
                    Toast.makeText(signupActivity.this, "請輸入密碼", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(psweditText2)){
                    Toast.makeText(signupActivity.this, "請再次輸入密碼", Toast.LENGTH_SHORT).show();
                    return;
                }
                if((psweditText1.equals(psweditText2)==false)){
                    Toast.makeText(signupActivity.this, "密碼不一致請重新輸入", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(emaileditText, psweditText1)
                        .addOnCompleteListener(signupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(signupActivity.this, "註冊成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent();
                                    intent.setClass(signupActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(signupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    public void gotoSignIn(View v) {
        Intent it = new Intent(this, MainActivity.class);

        startActivity(it);
    }
}

/*
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
}*/
