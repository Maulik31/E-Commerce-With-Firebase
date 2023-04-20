package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_commerce.Model.Category;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCategory extends AppCompatActivity {
    EditText AddCategory;
    Button Submit;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_category);

        AddCategory = findViewById(R.id.AddCat);
        Submit = findViewById(R.id.btnAddCat);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("category");
                String categoryName = AddCategory.getText().toString();

                Category model = new Category(categoryName);
                reference.child(categoryName).setValue(model);
                Toast.makeText(AddCategory.this, "Category Added Successfully..!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddCategory.this,AdminHomePage.class));

            }
        });
    }
}