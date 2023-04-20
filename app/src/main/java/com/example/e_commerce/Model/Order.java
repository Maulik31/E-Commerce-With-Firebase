package com.example.e_commerce.Model;

import java.util.Date;

public class Order {



    String orderId;
    String productName;
    String UserName;
    String Price;
    String Address;

    public Order(String productName, String userName, String price,String address,String orderId) {
        this.orderId = orderId;
        this.productName = productName;
        UserName = userName;
        Price = price;
        Address = address;
    }

    public Order() {
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
