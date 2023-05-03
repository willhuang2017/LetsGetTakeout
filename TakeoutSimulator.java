import java.util.Scanner;

// Create TakeOutSimulator class here
public class TakeOutSimulator{
  private Customer customer;
  private FoodMenu menu;
  private Scanner input;

  public TakeOutSimulator(Customer customer, FoodMenu menu, Scanner input){
    this.customer = customer;
    this.menu = menu;
    this.input = input;
  }

  private <T> T getOutputOnInput(String userInputPrompt, IntUserInputRetriever<T> intUserInputRetriever){
    System.out.println(userInputPrompt);
    while(true){
      if(this.input.hasNextInt()){
        int order = this.input.nextInt();
        try{
          return intUserInputRetriever.produceOutputOnIntUserInput(order);
        } catch(IllegalArgumentException e) {
          System.out.println(order + " is not a valid input. Try Again!");
        }
      } else {
        System.out.println("Input needs to be an 'int' type.");
        this.input.next();
      }
    }

  }

  public boolean shouldSimulate(){
    String userPrompt = "Enter 1 to CONTINUE simulation or 0 to EXIT program:";
    IntUserInputRetriever<Boolean> intUserInputRetriever = selection -> {
      if (selection == 1) {
        if(this.customer.getMoney() >= this.menu.getLowestCostFood().getPrice()){
          return true;
        } else {
          System.out.println("You don't have enough money to continue shopping :( - ending simulation...");
          return false;
        }
      } else if (selection == 0) {
        System.out.println("Ending Simulation");
        return false;
      } else {
        throw new IllegalArgumentException();
      }
    };
    return (Boolean) getOutputOnInput(userPrompt, intUserInputRetriever);
  }

  public Food getMenuSelection(){
    String userPrompt = "Today's Menu Options!\n\n" + menu.toString() + "\nChoose a menu item!: ";
    IntUserInputRetriever<Food> intUserInputRetriever = selection -> {
      Food food = this.menu.getFood(selection);
      if(food == null){
        throw new IllegalArgumentException();
      }
      return food;
    };
    return (Food) getOutputOnInput(userPrompt, intUserInputRetriever);
  }

  public boolean isStillOrderingFood(){
    String userPrompt = "Enter 1 to CONTINUE shopping or 0 to CHECKOUT: ";
    IntUserInputRetriever<Boolean> intUserInputRetriever = selection -> {
      if(selection == 1){
        return true;
      } else if (selection == 0){
        return false;
      } else {
        throw new IllegalArgumentException();
      }
    };
    return (Boolean) getOutputOnInput(userPrompt, intUserInputRetriever);
  }

  public void checkoutCustomer(ShoppingBag<Food> shoppingBag){
    System.out.println("Processing payment...");
    int remaining = this.customer.getMoney() - shoppingBag.getTotalPrice();
    System.out.println("Your remaining money: $"+remaining);
    this.customer.setMoney(remaining);
    System.out.println("Thank you and enjoy your food!");
  }
  
  public void takeOutPrompt(){
    ShoppingBag<Food> shoppingBag = new ShoppingBag<>();
    int customerMoneyLeft = this.customer.getMoney();
    while(isStillOrderingFood()){
      System.out.println("You have $"+customerMoneyLeft + " left to spend");
      Food order = this.getMenuSelection();
      int price = order.getPrice();
      if(customerMoneyLeft >= price){
        customerMoneyLeft = customerMoneyLeft - price;
        shoppingBag.addItem(order);
      } else {
        System.out.println("Oops! Looks like you don't have enough for that. Choose another item or checkout.");
      }
    }
    this.checkoutCustomer(shoppingBag);
  }

  public void startTakeOutSimulator(){
    while(shouldSimulate()){
      System.out.println("Welcome "+this.customer.getName() + " to the Bakery of Goods");
      this.takeOutPrompt();
    }
  }
}
