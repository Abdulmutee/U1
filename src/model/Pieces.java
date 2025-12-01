package model;

public enum Pieces {
    BOUNTY(67),
    ICECREAM(41),
    WATER(21);

    private final int price;

    Pieces(int price) {
        this.price = price;
    }
    public int getPrice(){
        return price;
    }
}
