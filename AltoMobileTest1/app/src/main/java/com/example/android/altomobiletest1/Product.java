package com.example.android.altomobiletest1;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String name;
    private float price;
    ArrayList<Subproducts> subproducts;

    public Product(String name, float price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ArrayList<Subproducts> getListSubproducts() {
        return subproducts;
    }


}
