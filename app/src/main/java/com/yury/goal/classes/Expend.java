package com.yury.goal.classes;

/**
 * Created by deyvidyury on 20/08/16.
 */
public class Expend {
    private static int ID;
    private String name;
    private double price;
    private int expendId;

    public Expend(String name, double price) {
        this.name = name;
        this.price = price;
        this.expendId = ID;
        ID += 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getExpendId() {
        return expendId;
    }
}
