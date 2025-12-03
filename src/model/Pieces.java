package model;

public class Pieces extends Product {

    public Pieces(String name, int price){
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public String displayProduct() {
        return "Name: " + this.name + ", Price:" + this.price;
    }
}
