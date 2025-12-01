package controller;

// 1. Skapa en order direkt
// 2. Om man trycker på order då skickas den gamla och en ny skapas.

//only imports what is strictly necessary from view-package
import model.Cake;
import model.Fillings;
import model.Pieces;
import view.MainFrame;
import view.ButtonType;

public class Controller {
    private MainFrame view;
    private ButtonType currentLeftMenu = ButtonType.NoChoice;
    private String [] cakeMenuString; // for test purposes only
    private String [] perUnitItemMenuString; // for test purposes only
    private String [] orderHistoryMenuString; // for test purposes only
    private String [] order1Simulation; // for test purposes only
    private String [] currentOrderArray; // for test purposes only
    private double costCurrentOrder = 0; // for test purposes only
    private int nbrOfOrders = 0; // for test purposes only

    public Controller() {
        view = new MainFrame(1000, 500, this);
        loadStringTestValues(); //for test purposes - remove when not needed more
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableViewSelectedOrderButton();
    }

    //This method is only used for test purposes - remove when no longer needed
    private void loadStringTestValues() {
        cakeMenuString = new String[10];
        perUnitItemMenuString = new String[10];
        orderHistoryMenuString = new String[10];
        order1Simulation = new String[10];
        currentOrderArray = new String[10];

        Cake strawberryCake = new Cake("Strawberry Cake", 50, 6, Fillings.STRAWBERRY, Fillings.VANILLA);
        Cake ChockoHummusCake = new Cake("ChockoHummusCake", 1000, 1, Fillings.CHOCOLATE, Fillings.HUMMUS);
        Cake weddingCake = new Cake("Wedding Special Cake", 99, 10, Fillings.CHOCOLATE, Fillings.VANILLA);
        Cake bestCake = new Cake("Best Cake", 10000, 1, Fillings.LICORICE, Fillings.HUMMUS);
        cakeMenuString[0] = strawberryCake.displayProducts();
        cakeMenuString[1] = ChockoHummusCake.displayProducts();
        cakeMenuString[2] = weddingCake.displayProducts();
        cakeMenuString[3] = bestCake.displayProducts();


        Pieces bounty = Pieces.BOUNTY;
        Pieces iceCream = Pieces.ICECREAM;
        Pieces water = Pieces.WATER;
        perUnitItemMenuString[0] = bounty.displayPieces();
        perUnitItemMenuString[1] = iceCream.displayPieces();
        perUnitItemMenuString[2] = water.displayPieces();

        orderHistoryMenuString[0] = "order1: kostnad:100";
        orderHistoryMenuString[1] = "order2: kostand:200";

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
                    currentOrderArray[nbrOfOrders] = cakeMenuString[selectionIndex].toString(); //for test purposes - needs to be replaced with solution of finding chosen menu item matching architecture for model
                    break;
                case PerUnitItem:
                    currentOrderArray[nbrOfOrders] = perUnitItemMenuString[selectionIndex].toString(); //see comment for case above
                    break;
            }
            nbrOfOrders++; //for test purposes - need to be removed or changed when model for handling orders is implemented
            costCurrentOrder = costCurrentOrder + 100; //for test purposes - replace with calculation of cost when how orders are handled is implemented in model
            view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
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
        view.populateLeftPanel(cakeMenuString);
        view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        view.enableAllButtons();
        view.disableCakeMenuButton();
        view.disableViewSelectedOrderButton();
    }

    public void setToPerUnitItemMenu() {
        currentLeftMenu = ButtonType.PerUnitItem;
        view.populateLeftPanel(perUnitItemMenuString);
        view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        view.enableAllButtons();
        view.disablePerUnitItemMenuButton();
        view.disableViewSelectedOrderButton();
    }

    public void setToOrderHistoryMenu() {
        currentLeftMenu = ButtonType.OrderHistory;
        view.clearRightPanel();
        view.populateLeftPanel(orderHistoryMenuString);
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableOrderButton();
    }

    public void placeOrder() {
        System.out.println("Pressed Order to create a new order"); //for test purposes - remove when not needed more
        currentOrderArray = new String[10];  // for test purposes - remove when not needed more
        costCurrentOrder = 0;
        view.clearRightPanel(); //Removes information from right panel in GUI
        view.setTextCostLabelRightPanel("TOTAL COST: 0");
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableViewSelectedOrderButton();
    }


}
