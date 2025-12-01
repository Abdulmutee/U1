package model;

public class Order {

    private Products[] products;

    public Order() {
        this.products = new Products[10];
    }

    // Tillfälligt, räknar ut totala priset
    public int totalPrice() {
        return 0;
    }

    public void addProduct(Products product) {
        for (int i = 0; i< this.products.length; i++) {
            if (this.products[i] == null) {
                this.products[i] = product;
                return;
            }
        }
        Products[] newList = new Products[this.products.length+5];

        for (int i = 0; i< this.products.length; i++) {
            newList[i] = this.products[i];
        }

        newList[this.products.length] = product;

        this.products = newList;
    }
}
