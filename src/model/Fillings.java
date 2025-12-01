package model;

public enum Fillings {
    STRAWBERRY(250),
    HUMMUS(251),
    VANILLA(50);

    private final int price;

    Fillings(int price) {
        this.price = price;
    }
    public int getPrice(){
        return price;
    }
}
