package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.e_commerce.Adapter.DeleteProductViewAdapter;
import com.example.e_commerce.Adapter.ShowUserAdapter;
import com.example.e_commerce.Model.Product;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteProduct extends AppCompatActivity {

    RecyclerView recview;
    DeleteProductViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);

        recview=(RecyclerView) findViewById(R.id.recview2);
        recview.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("product"), Product.class)
                        .build();

        adapter = new DeleteProductViewAdapter(options);
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
        startActivity(new Intent(DeleteProduct.this,AdminHomePage.class));
    }
}