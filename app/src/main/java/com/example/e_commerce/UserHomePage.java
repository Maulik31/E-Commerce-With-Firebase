package com.example.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_commerce.Adapter.myadapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.Adapter.myadapter;
import com.example.e_commerce.Model.Product;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class UserHomePage extends AppCompatActivity {


    RecyclerView recview;
    myadapter adapter;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_home_page);
      button = (Button) findViewById(R.id.btnCheckout);
      recview=(RecyclerView) findViewById(R.id.recview);
      recview.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");

        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product"), Product.class)
                        .build();

        adapter=new myadapter(options,UserHomePage.this);
        recview.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHomePage.this,LoginPage.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    public void onBackPressed() {
        startActivity(new Intent(UserHomePage.this,LoginPage.class));
    }
}