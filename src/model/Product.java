package model;

public abstract class Product {
    protected String name;
    protected int price;

    public abstract String displayProduct();

    public abstract int getPrice();
}
