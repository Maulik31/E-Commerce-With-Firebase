package com.example.e_commerce.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.Model.Product;
import com.example.e_commerce.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowUserAdapter extends FirebaseRecyclerAdapter<Product, ShowUserAdapter.myviewholder> {

    FirebaseDatabase database;
    DatabaseReference reference;



    public ShowUserAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);


    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull Product model) {
        holder.productName.setText("Product Name:"+model.getProductName());
        holder.productPrice.setText("Product Price :"+model.getProductPrice());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.productName.getContext());
                builder.setTitle("Delete Product");
                builder.setMessage("Are You Sure You Want to Delete...?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("product")
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
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_users,parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder{
        TextView productName, productPrice;
        Button delete;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            productName = (TextView)itemView.findViewById(R.id.ProductName);
            productPrice = (TextView)itemView.findViewById(R.id.ProductCategory);
            delete = (Button) itemView.findViewById(R.id.btnDelete);


        }
    }
}
