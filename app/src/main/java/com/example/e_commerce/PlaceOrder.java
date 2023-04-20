package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce.Model.Order;
import com.example.e_commerce.Model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.util.Random;

public class PlaceOrder extends AppCompatActivity {
    TextView t1,t2;
    EditText e1,e2;
    Button placeOrder;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        final String productName=getIntent().getStringExtra("pname");
        final String productPrice=getIntent().getStringExtra("pprice");

        t1=findViewById(R.id.itemname);
        t2=findViewById(R.id.custname);
        e1=findViewById(R.id.etName);
        e2=findViewById(R.id.etAddress);
        placeOrder=findViewById(R.id.btnplord);
        t1.setText("Product Name: " + productName);
        t2.setText("Product Price: $" + productPrice);

        int min = 100;
        int max = 10000;
        Random rand = new Random();



        int rnum =rand.nextInt((max - min) + 1) + min;
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("order");
                String userName = e1.getText().toString();
                String userAddress = e2.getText().toString();
                String pPrice = productPrice;
                String pName = productName;
                String oId = Integer.toString(rnum);

                Order model = new Order(pName,userName,pPrice,userAddress,oId);
                reference.child(oId).setValue(model);

                Toast.makeText(PlaceOrder.this,"Thank You For Your Order",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PlaceOrder.this,UserHomePage.class));
            }
        });



    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PlaceOrder.this,UserHomePage.class));
    }
}