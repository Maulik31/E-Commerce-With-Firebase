package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    EditText admuser,admpass;
    Button admlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_login);
        admuser=(EditText)findViewById(R.id.admuser);
        admpass=(EditText)findViewById(R.id.admpass);
        admlog=(Button)findViewById(R.id.admlogin);

        admlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!((TextUtils.isEmpty(admuser.getText().toString()))||(TextUtils.isEmpty(admpass.getText().toString())))){
                    if (admuser.getText().toString().equals("a")&&admpass.getText().toString().equals("a")){
                        Intent intent=new Intent(AdminLogin.this,AdminHomePage.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(AdminLogin.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(AdminLogin.this, "Enter ID and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    public void onBackPressed() {
        startActivity(new Intent(AdminLogin.this,MainPage.class));
    }


}