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
        if(filling2==null) {
            return price * size + filling1.getPrice();

        } else {
            return price * size + filling1.getPrice() + filling2.getPrice();
        }
    }

    public String displayProducts(){
        if (filling2 != null) {
            return "Cake name: " + name + ", Filling 1: " + filling1 + ", Filling 2: " + filling2 + " Size: " + size + ", Price: " + calculatePrice();
        }
        else {
            return "Cake name: "+ name + ", Filling " + filling1 + " Size: " + size +  ", Price: "  + calculatePrice();
        }

    }
}
