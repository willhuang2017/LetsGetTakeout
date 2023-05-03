import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
      FoodMenu menu = new FoodMenu();
      Scanner scanner = new Scanner(System.in);
      System.out.println("Please Enter Your Name");
      String name = scanner.next();
      Customer customer = new Customer(name,0);

      TakeOutSimulator test = new TakeOutSimulator(customer,menu,scanner);
      test.startTakeOutSimulator();
      }
}
