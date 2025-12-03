package model;

import java.util.Random;

public class Order {


    private int id;
    private Random random;
    private Product[] products;

    public Order() {
        this.products = new Product[10];

        this.random = new Random();
        this.id = random.nextInt(1,100000);
        

    }

    public int getId() {
        return id;
    }

    // Tillfälligt
    public double getTotalPrice() {
        double totalPrice = 0;

        for (Product product : products){
            if (product != null) {
                totalPrice += product.getPrice();
            }
        }
        return totalPrice;
    }

    public void addProduct(Product product) {
        for (int i = 0; i< this.products.length; i++) {
            if (this.products[i] == null) {
                this.products[i] = product;
                return;
            }
        }
        Product[] newList = new Product[this.products.length+5];

        for (int i = 0; i< this.products.length; i++) {
            newList[i] = this.products[i];
        }

        newList[this.products.length] = product;

        this.products = newList;
    }

    public double calculatePrice() {
        double summ = 0;
        for (Product product : products){
            if (product != null) {
                summ += product.getPrice();
            }
        }
        return summ;
    }

    public String[] getProductsAsString() {
        String[] productsAsString = new String[products.length];

        for (int i = 0; i< products.length; i++) {
            if (products[i] != null) {
                productsAsString[i] = products[i].displayProduct();
            }
        }
        return productsAsString;
    }
}
