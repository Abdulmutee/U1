package controller;

// 1. Rad 137, fixa att kunna se vad som är i historik.
// 2. Se till att test metoder är tagna bort/ersatta.

//only imports what is strictly necessary from view-package
import model.*;
import view.MainFrame;
import view.ButtonType;

public class Controller {
    private MainFrame view;
    private ButtonType currentLeftMenu = ButtonType.NoChoice;
    private Cake [] cakeList; // for test purposes only
    private String[] cakeListString;
    private Pieces[] piecesList;
    private String[] piecesListString;
    private OrderHistory orderHistory;
    private String [] order1Simulation; // for test purposes only
    private double costCurrentOrder = 0; // for test purposes only
    private int nbrOfOrders = 0; // for test purposes only
    private Order order;

    public Controller() {

        cakeList  = new Cake[10];
        cakeListString = new String[cakeList.length];
        piecesList = new Pieces[10];
        piecesListString = new String[piecesList.length];

        Cake strawberryCake = new Cake("Strawberry Cake", 50, 6, Fillings.STRAWBERRY, Fillings.VANILLA);
        Cake ChockoHummusCake = new Cake("ChockoHummusCake", 1000, 1, Fillings.CHOCOLATE, Fillings.HUMMUS);
        Cake weddingCake = new Cake("Wedding Special Cake", 99, 10, Fillings.CHOCOLATE, Fillings.VANILLA);
        Cake bestCake = new Cake("Best Cake", 10000, 1, Fillings.LICORICE, Fillings.HUMMUS);

        cakeList[0] = strawberryCake;
        cakeList[1] = ChockoHummusCake;
        cakeList[2] = weddingCake;
        cakeList[3] = bestCake;

        Pieces bounty = new Pieces("Bounty", 21);
        Pieces iceCream = new Pieces("Cream", 41);
        Pieces water = new Pieces("Water", 67);
        piecesList[0] = bounty;
        piecesList[1] = iceCream;
        piecesList[2] = water;

        for (int i = 0; i<cakeList.length; i++){
            if (cakeList[i]!=null) {
                cakeListString[i] = cakeList[i].displayProducts();
            }
        }

        for (int i = 0; i<piecesList.length; i++){
            if (piecesList[i]!=null) {
                piecesListString[i] = piecesList[i].displayProducts();
            }
        }

        this.order = new Order();
        this.orderHistory = new OrderHistory();

        view = new MainFrame(1000, 500, this);
        loadStringTestValues(); //for test purposes - remove when not needed more
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableViewSelectedOrderButton();



    }

    //This method is only used for test purposes - remove when no longer needed
    private void loadStringTestValues() {

        order1Simulation = new String[10];
        order1Simulation[0] = "Order 1";
        order1Simulation[1] = "tårta1 Pris1";
        order1Simulation[2] = "tårta2 Pris2";
        order1Simulation[3] = "vetebulle Pris11";

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
        System.out.println("Index selection left panel: " + selectionIndex); //for test purposes  - remove when not needed

        if (selectionIndex != -1){ // if something is selected in the left menu list
            switch (currentLeftMenu) { //This might need to change depending on architecture
                case Cake:
                    order.addProduct(cakeList[selectionIndex]); //for test purposes - needs to be replaced with solution of finding chosen menu item matching architecture for model
                    break;
                case PerUnitItem:
                    order.addProduct(piecesList[selectionIndex]);
                    break;
            }
            nbrOfOrders++; //for test purposes - need to be removed or changed when model for handling orders is implemented
            costCurrentOrder = order.calculatePrice(); //for test purposes - replace with calculation of cost when how orders are handled is implemented in model



            view.populateRightPanel(order.getProductsAsString()); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
            view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        }

    }

    public void viewSelectedOrder(int selectionIndex){
        System.out.println("Index selection left panel: " + selectionIndex); //for test purposes  - remove when not needed

        if ((selectionIndex != -1) && currentLeftMenu==ButtonType.OrderHistory){
            costCurrentOrder = 100; //for test purposes - replace with calculation of cost when how orders are handled is implemented in model
            view.populateRightPanel(order1Simulation); //update left panel with order details - this takes a shortcut in updating the entire information in the panel not just adds to the end
            view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        }
    }

    public void setToCakeMenu() {
        currentLeftMenu = ButtonType.Cake;
        view.populateLeftPanel(cakeListString);
        view.populateRightPanel(order.getProductsAsString()); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        view.enableAllButtons();
        view.disableCakeMenuButton();
        view.disableViewSelectedOrderButton();
    }

    public void setToPerUnitItemMenu() {
        currentLeftMenu = ButtonType.PerUnitItem;
        view.populateLeftPanel(piecesListString);
        view.populateRightPanel(order.getProductsAsString()); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
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
        System.out.println("Pressed Order to create a new order"); //for test purposes - remove when not needed more

        orderHistory.addOrder(order);
        this.order = new Order();

        costCurrentOrder = 0;
        view.clearRightPanel(); //Removes information from right panel in GUI
        view.setTextCostLabelRightPanel("TOTAL COST: 0");
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableViewSelectedOrderButton();
    }


}
