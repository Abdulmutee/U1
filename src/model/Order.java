package model;

public class Order {

    private Product[] products;

    public Order() {
        this.products = new Product[10];
    }

    // Tillfälligt
    public int totalPrice() {
        return 0;
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
}
