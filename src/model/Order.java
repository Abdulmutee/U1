package model;

import java.util.Random;

public class Order {

    private int id;
    private int totalPrice;
    private Product[] products;
    private Random random;

    public Order() {
        this.products = new Product[10];
        random = new Random();
        this.id = random.nextInt(1,100001);
    }

    public int getId() {
        return id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void addProduct(Product product) {
        for (int i = 0; i < this.products.length; i++) {
            if (this.products[i] == null) {
                this.products[i] = product;
                return;
            }
        }
        Product[] newList = new Product[this.products.length + 5];

        for (int i = 0; i < this.products.length; i++) {
            newList[i] = this.products[i];
        }

        newList[this.products.length] = product;

        this.products = newList;
    }

    public String[] getProductsAsString() {
        String[] productsString = new String[products.length];

        for (int i = 0; i < this.products.length; i++) {
            if (products[i]!=null){
                productsString[i] = this.products[i].displayProducts();
            }
        }
        return productsString;
    }

    public int calculatePrice(){
        int summ = 0;
        for (Product product : products){
            if (product != null){
                summ += product.getPrice();
            }
        }
        this.totalPrice = summ;
        return summ;
    }

}
