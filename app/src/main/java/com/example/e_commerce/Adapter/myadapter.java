package com.example.e_commerce.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commerce.MainActivity;
import com.example.e_commerce.Model.Order;
import com.example.e_commerce.Model.Product;
import com.example.e_commerce.Model.User;
import com.example.e_commerce.PlaceOrder;
import com.example.e_commerce.R;
import com.example.e_commerce.RegisterPage;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<Product,myadapter.myviewholder> {
    FirebaseDatabase database;
    DatabaseReference reference;
Context context;



    public myadapter(@NonNull FirebaseRecyclerOptions<Product> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Product model) {
       holder.name.setText("Name:"+model.getProductName());
        holder.category.setText("Category:"+model.getProductCategory());
        holder.desc.setText("Desc:"+model.getProductDescription());
        holder.price.setText("Price:$ "+model.getProductPrice());
        Glide.with(holder.img.getContext()).load(model.getProductImage()).into(holder.img);

        holder.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, PlaceOrder.class);
                intent.putExtra("pname",model.getProductName());
                intent.putExtra("pprice",model.getProductPrice());
                context.startActivity(intent);

            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder{
        TextView name,desc,category,price;
        CircleImageView img;

        Button btnOrder;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.ProductName);
            desc = (TextView)itemView.findViewById(R.id.ProductDesc);
            category = (TextView)itemView.findViewById(R.id.ProductCategory);
            price = (TextView)itemView.findViewById(R.id.ProductPrice);
            img = (CircleImageView)itemView.findViewById(R.id.img1);
            btnOrder = (Button) itemView.findViewById(R.id.btnOrder);




        }
    }
}
