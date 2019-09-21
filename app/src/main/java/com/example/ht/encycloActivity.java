package com.example.ht;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class encycloActivity extends AppCompatActivity {

    Button check, warm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclo);

        check = findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(encycloActivity.this);
                final View popView= inflater.inflate(R.layout.check,null);
                final AlertDialog.Builder builder=new AlertDialog.Builder(encycloActivity.this);
                builder.setView(popView);
                final AlertDialog dialog=builder.setNegativeButton("關閉",null).create();
                dialog.show();
            }
        });

        warm = findViewById(R.id.warning);
        warm.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(encycloActivity.this)
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
            }
        });
    }

    public void open1(View view){
        Intent browserIntent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://tspc.tw/tspc/portal/theme/index.jsp?sno=95"));
        startActivity(browserIntent1);
    }

    public void open2(View view){
        Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jtf.org.tw/psyche/melancholia_02/"));
        startActivity(browserIntent2);
    }

    public void gotohelp(View v) {
        Intent it = new Intent(this, helpGPSActivity.class );
        startActivity(it);
    }
}
