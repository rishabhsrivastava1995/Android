package com.example.rishabhr.myapplication;

import android.provider.BaseColumns;

public class Product implements BaseColumns{
    public static final String TABLE_NAME = "products";
    public static final String NAME = "name";
    public static final String COST = "cost";

    private long id;
    private String name;
    private double cost;

    public Product() {
        this(1, "", 0.0);
    }

    public Product(String name, double cost) {
        this(1, name, cost);
    }

    public Product(long id, String name, double cost) {
        this.id = id;
        this.cost = cost;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
