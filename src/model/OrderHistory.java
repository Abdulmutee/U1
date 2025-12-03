package model;

public class OrderHistory {
    Order[] orders;

    public OrderHistory() {
        this.orders = new Order[10];
    }

    public void addOrder(Order order) {
        for (int i = 0; i < this.orders.length; i++) {
            if (this.orders[i] == null) {
                this.orders[i] = order;
                return;
            }

        }
        Order[] newList = new Order[this.orders.length + 5];

        for (int i = 0; i < this.orders.length; i++) {
            newList[i] = this.orders[i];
        }

        newList[this.orders.length] = order;

        this.orders = newList;
    }

    public String[] getOrdersAsString() {
        String[] OrdersString = new String[orders.length];

        for (int i = 0; i < this.orders.length; i++) {
            if (orders[i]!=null){
                OrdersString[i] = "Order ID: " + this.orders[i].getId() + ", Price: "+ this.orders[i].getTotalPrice();
            }
        }
        return OrdersString;
    }



}
