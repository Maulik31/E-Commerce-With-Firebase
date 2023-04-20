package com.example.e_commerce.Model;

public class Category {

    String catName;


    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Category() {
    }

    public Category( String catName) {

        this.catName = catName;
    }

}
