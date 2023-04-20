package com.example.e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.index.qual.PolyUpperBound;

public class LoginPage extends AppCompatActivity {
    EditText loginEmail, loginPass;
    Button loginbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_page);

        loginEmail = findViewById(R.id.logemail);
        loginPass = findViewById(R.id.logpass);
        loginbtn = findViewById(R.id.btnlogin);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUser();
            }
        });
    }
    public void checkUser(){
        String userName = loginEmail.getText().toString().trim();
        String userPassword = loginPass.getText().toString().trim();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUserDatabase = reference.orderByChild("name").equalTo(userName);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    loginEmail.setError(null);
                    String userPasswordDb = snapshot.child(userName).child("password").getValue(String.class);

                    if (userPasswordDb.equals(userPassword)) {
                                String username = snapshot.child(userName).child("name").getValue(String.class);
                                Intent intent = new Intent(LoginPage.this,MainActivity.class);
                                intent.putExtra("name",username);


                        Toast.makeText(LoginPage.this, "Login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginPage.this,UserHomePage.class));
                    } else {
                        loginPass.setError("Invalid Password");
                        loginPass.requestFocus();
                    }
                } else {
                    loginEmail.setError("Invalid UserName");
                    loginPass.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void onBackPressed() {
        startActivity(new Intent(LoginPage.this,MainPage.class));
    }
}

