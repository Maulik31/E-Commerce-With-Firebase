package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {
    Button reg,log;
    TextView admlog,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_page);
        reg=(Button)findViewById(R.id.btnreg);
        log=(Button)findViewById(R.id.btnlog);
        admlog=(TextView)findViewById(R.id.admlog);

        about=(TextView)findViewById(R.id.tvAbout);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainPage.this,LoginPage.class);
                startActivity(intent);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainPage.this,RegisterPage.class);
                startActivity(intent);
            }
        });
        admlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this,AdminLogin.class));
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this,About.class));
            }
        });
    }
    public void onBackPressed(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
    }
