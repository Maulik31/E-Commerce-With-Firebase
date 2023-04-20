package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminHomePage extends AppCompatActivity {
    Button addCategory, addProduct,showOrder,deleteProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_home_page);

        addCategory =findViewById(R.id.btn_add_category);
        addProduct =findViewById(R.id.btn_add_product);
        showOrder = findViewById(R.id.btn_view_order);
        deleteProduct = findViewById(R.id.btn_delete_product);


        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomePage.this,AddCategory.class));
            }
        });
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomePage.this, AddProduct.class));
            }
        });
        showOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomePage.this,ShowOrder.class));
            }
        });

        deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomePage.this,DeleteProduct.class));
            }
        });
    }
    public void onBackPressed() {
        startActivity(new Intent(AdminHomePage.this,AdminLogin.class));
    }
}