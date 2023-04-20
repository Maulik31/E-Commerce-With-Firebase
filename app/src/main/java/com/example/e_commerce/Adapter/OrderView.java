package com.example.e_commerce.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commerce.Model.Order;
import com.example.e_commerce.Model.Product;
import com.example.e_commerce.PlaceOrder;
import com.example.e_commerce.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderView extends FirebaseRecyclerAdapter<Order,OrderView.myviewholder> {

    FirebaseDatabase database;
    DatabaseReference reference;


    public OrderView(@NonNull FirebaseRecyclerOptions<Order> options) {
        super(options);

    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull Order model) {
        holder.productName.setText("Product Name:"+model.getProductName());
        holder.productPrice.setText("Product Price $:"+model.getPrice());
        holder.userName.setText("Ordered By :"+model.getUserName());
        holder.userAddress.setText("User Address is: "+model.getAddress());
        holder.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.productName.getContext());
                builder.setTitle("Order Completed");
                builder.setMessage("Is this order is completed...?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("order")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });




    }

    @NonNull
    @Override
    public OrderView.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderview,parent,false);
        return new OrderView.myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder{
        TextView productName,userName,userAddress,productPrice;
        Button btnDone;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            productName = (TextView)itemView.findViewById(R.id.ProductName);
            productPrice = (TextView)itemView.findViewById(R.id.ProductCategory);
            userName = (TextView)itemView.findViewById(R.id.ProductDesc);
            userAddress = (TextView)itemView.findViewById(R.id.ProductPrice);
            btnDone = (Button) itemView.findViewById(R.id.btnDone);

        }
    }
}
