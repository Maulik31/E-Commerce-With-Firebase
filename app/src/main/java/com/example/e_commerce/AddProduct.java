package com.example.e_commerce;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerce.Model.Product;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddProduct extends AppCompatActivity {

    EditText ProductName,ProductDesc,ProductPrice;
    Button AddProduct,browse;
    Spinner spinner;
    FirebaseDatabase database;
    DatabaseReference reference;
    Uri filepath;
    ImageView img;
    Bitmap bitmap;

    ArrayAdapter<String> dataAdapter;
    List<String> categories = new ArrayList<String>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_product);

        AddProduct = findViewById(R.id.btnAddProduct);
        img=findViewById(R.id.img);
        browse=findViewById(R.id.browse);
        spinner = findViewById(R.id.spinner);

        fetchCategoryData();

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        //------------------------------------
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withActivity(AddProduct.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response)
                            {
                                Intent intent=new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                               startActivityForResult(Intent.createChooser(intent,"Select Image File"),1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        AddProduct.setOnClickListener(view -> {
            uploadtofirebase();
        });

    }

    private void fetchCategoryData() {
        reference = database.getInstance().getReference("category");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    categories.add(dataSnapshot.child("catName").getValue().toString());
                }
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode==1  && resultCode==RESULT_OK)
        {
            filepath=data.getData();
            try{
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            }catch (Exception ex)
            {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadtofirebase()
    {
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setTitle("File Uploader");
        dialog.show();

        ProductName = findViewById(R.id.ProductName);
        ProductDesc = findViewById(R.id.ProductDesc);
        ProductPrice = findViewById(R.id.ProductPrice);

        FirebaseStorage storage=FirebaseStorage.getInstance();
        final StorageReference uploader=storage.getReference("Image1"+new Random().nextInt(50));

        uploader.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                    {
                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri){

                                dialog.dismiss();
                                database = FirebaseDatabase.getInstance();
                                reference = database.getReference("product");
                                String productName = ProductName.getText().toString();
                                String productDesc = ProductDesc.getText().toString();
                                String productPrice = ProductPrice.getText().toString();
                                String productCategory= spinner.getSelectedItem().toString();


                                Product product_model = new Product(productName,productDesc,productPrice,productCategory,uri.toString());
                                reference.child(productName).setValue(product_model);
                                Toast.makeText(AddProduct.this, "Product Added Successfully..!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddProduct.this,AdminHomePage.class));
                            }
                        });
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot)
                    {
                        float percent=(100*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                        dialog.setMessage("Uploaded :"+(int)percent+" %");
                    }
                });
    }


}