package model;

public class Cake extends Products {
    private Fillings filling1;
    private Fillings filling2;
    private int size;


    public Cake(String name, int price, int size, Fillings filling1,  Fillings filling2) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.filling1 = filling1;
        this.filling2 = filling2;
    }


    public int calculatePrice() {
        return price * size + filling1.getPrice() + filling2.getPrice();
    }

    public String displayProducts(){
            return "Cake name: " + name + ", Filling 1: " + filling1 + ", Filling 2: " + filling2 + " Size: " + size + ", Price: " + calculatePrice();
    }
}
