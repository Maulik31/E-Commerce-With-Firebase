package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.e_commerce.Adapter.OrderView;
import com.example.e_commerce.Adapter.myadapter;
import com.example.e_commerce.Model.Order;
import com.example.e_commerce.Model.Product;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ShowOrder extends AppCompatActivity {

    RecyclerView recview;
    OrderView adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_show_order);

        recview=(RecyclerView) findViewById(R.id.recview1);
        recview.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Order> options =
                new FirebaseRecyclerOptions.Builder<Order>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("order"), Order.class)
                        .build();

        adapter = new OrderView(options);
        recview.setAdapter(adapter);


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
        startActivity(new Intent(ShowOrder.this,AdminHomePage.class));
    }
}