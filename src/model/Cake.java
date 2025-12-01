package model;

public class Cake extends Products {
    private Fillings filling1;
    private Fillings filling2;

    private int size;
    public Cake(String name, int price, int size, Fillings filling1) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.filling1 = filling1;
    }

    public Cake(String name, int price, int size, Fillings filling1,  Fillings filling2) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.filling1 = filling1;
        this.filling2 = filling2;
    }


    public int calculatePrice() {
        return price * size + filling1.getPrice();
    }

    public String displayProducts(){
        return name + " " + fillings + " " + size + " " + fillings.getPrice();
    }
}
