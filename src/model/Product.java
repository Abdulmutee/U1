package model;

public abstract class Product {
    protected String name;
    protected int price;

    public abstract String displayProducts();

    public abstract int getPrice();
}

