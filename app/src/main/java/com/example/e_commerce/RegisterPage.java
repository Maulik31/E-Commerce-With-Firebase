package com.example.e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_commerce.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RegisterPage extends AppCompatActivity {
    EditText name, address,email,password;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    Button sigupbtn;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_page);

        name = findViewById(R.id.etName);
        address = findViewById(R.id.etAddress);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        sigupbtn = findViewById(R.id.btnregister);

        sigupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                String nameuser = name.getText().toString();
                String emailuser = email.getText().toString();
                String addressuser = address.getText().toString();
                String passworduser = password.getText().toString();

                Query checkUserDatabase = reference.orderByChild("name").equalTo(nameuser);
                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(nameuser.isEmpty()){
                            name.setError("Enter Username");
                        } else if(snapshot.exists()) {
                            name.setError("Username Exist Enter New Username!");
                        } else if (!emailuser.matches(emailPattern)) {
                            email.setError("Enter Valid Email Address");
                       } else if (addressuser.isEmpty()) {
                            address.setError("Enter Address");
                       } else if (passworduser.isEmpty()) {
                        password.setError("Enter Password");
                       }else {
                            User model = new User(nameuser,emailuser,addressuser,passworduser);
                            reference.child(nameuser).setValue(model);

                            Toast.makeText(RegisterPage.this, "Successfully sign up", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterPage.this,MainActivity.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });



    }
    public void onBackPressed() {
        startActivity(new Intent(RegisterPage.this,MainPage.class));
    }
}