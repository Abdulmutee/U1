package controller;



//only imports what is strictly necessary from view-package
import model.*;
import view.MainFrame;
import view.ButtonType;


public class Controller {
    private MainFrame view;
    private ButtonType currentLeftMenu = ButtonType.NoChoice;
    private Cake [] cakeList;
    private String[] cakeListString;
    private Pieces[] piecesList;
    private String[] piecesListString;
    private OrderHistory orderHistory;
    private double costCurrentOrder = 0;
    private Order order;

    public Controller() {

        this.order = new Order();
        this.orderHistory = new OrderHistory();

        view = new MainFrame(1200, 500, this);
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableViewSelectedOrderButton();


    }

    //This method is called by class MainFrame when a button in the GUI is pressed
    public void buttonPressed(ButtonType button){

        switch (button) {
            case Add:
                addItemToOrder(view.getSelectionLeftPanel());
                break;

            case Cake:
                setToCakeMenu();
                break;

            case PerUnitItem:
                setToPerUnitItemMenu();
                break;

            case OrderHistory:
                setToOrderHistoryMenu();
                break;

            case Order:
                placeOrder();
                break;

            case ViewOrder:
                viewSelectedOrder(view.getSelectionLeftPanel());
                break;
        }
    }

    public void addItemToOrder(int selectionIndex) {

        if (selectionIndex != -1){ // if something is selected in the left menu list
            switch (currentLeftMenu) {
                case Cake:
                    order.addProduct(cakeList[selectionIndex]);
                    break;
                case PerUnitItem:
                    order.addProduct(piecesList[selectionIndex]);
                    break;
            }
            costCurrentOrder = order.calculatePrice();



            view.populateRightPanel(order.getProductsAsString()); //update right panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
            view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        }

    }

    public void viewSelectedOrder(int selectionIndex){

        if ((selectionIndex != -1) && currentLeftMenu==ButtonType.OrderHistory){

            if (orderHistory.orders[selectionIndex] != null) {
                costCurrentOrder = orderHistory.orders[selectionIndex].getTotalPrice();
                view.populateRightPanel(orderHistory.orders[selectionIndex].getProductsAsString()); //update right panel with order details - this takes a shortcut in updating the entire information in the panel not just adds to the end
                view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
            }
        }
    }

    public void setToCakeMenu() {

        cakeList  = new Cake[10];
        cakeListString = new String[cakeList.length];


        Cake strawberryCake = new Cake("Strawberry Cake", 50, 6, Fillings.STRAWBERRY, Fillings.VANILLA);
        Cake ChockoHummusCake = new Cake("ChockoHummusCake", 1000, 1, Fillings.CHOCOLATE, Fillings.HUMMUS);
        Cake weddingCake = new Cake("Wedding Special Cake", 99, 10, Fillings.CHOCOLATE, Fillings.VANILLA);
        Cake bestCake = new Cake("Best Cake", 10000, 1, Fillings.LICORICE, Fillings.HUMMUS);

        cakeList[0] = strawberryCake;
        cakeList[1] = ChockoHummusCake;
        cakeList[2] = weddingCake;
        cakeList[3] = bestCake;

        for (int i = 0; i<cakeList.length; i++){
            if (cakeList[i]!=null) {
                cakeListString[i] = cakeList[i].displayProducts();
            }
        }

        currentLeftMenu = ButtonType.Cake;
        view.populateLeftPanel(cakeListString);
        view.populateRightPanel(order.getProductsAsString()); //update right panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        view.enableAllButtons();
        view.disableCakeMenuButton();
        view.disableViewSelectedOrderButton();
    }

    public void setToPerUnitItemMenu() {
        piecesList = new Pieces[10];
        piecesListString = new String[piecesList.length];

        Pieces bounty = new Pieces("Bounty", 21);
        Pieces iceCream = new Pieces("Cream", 41);
        Pieces water = new Pieces("Water", 67);
        piecesList[0] = bounty;
        piecesList[1] = iceCream;
        piecesList[2] = water;

        for (int i = 0; i<piecesList.length; i++){
            if (piecesList[i]!=null) {
                piecesListString[i] = piecesList[i].displayProducts();
            }
        }

        currentLeftMenu = ButtonType.PerUnitItem;
        view.populateLeftPanel(piecesListString);
        view.populateRightPanel(order.getProductsAsString()); //update right panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        view.enableAllButtons();
        view.disablePerUnitItemMenuButton();
        view.disableViewSelectedOrderButton();
    }

    public void setToOrderHistoryMenu() {
        currentLeftMenu = ButtonType.OrderHistory;
        view.clearRightPanel();
        view.populateLeftPanel(orderHistory.getOrdersAsString());
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableOrderButton();
    }

    public void placeOrder() {
        if (order.getProducts()[0]!=null) { // Checks if orderlist is empty
            orderHistory.addOrder(order);
        }
        this.order = new Order();

        costCurrentOrder = 0;
        view.clearRightPanel(); //Removes information from right panel in GUI
        view.setTextCostLabelRightPanel("TOTAL COST: 0");
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableViewSelectedOrderButton();
    }


}
