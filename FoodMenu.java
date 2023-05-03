import java.util.ArrayList;
import java.util.List;

// Create FoodMenu class here
public class FoodMenu{
  private List<Food> menu = new ArrayList<>();

  public FoodMenu(){
    menu.add(new Food("Cake","Delicious",2));
    menu.add(new Food("Cookies","Good With Milk",4));
    menu.add(new Food("Water","Stay Quenched",10));
  }

  public Food getFood(int index){
    if(index > this.menu.size() || index < 1) return null;
    return this.menu.get(index - 1);
  }

  public Food getLowestCostFood(){
    Food lowest = this.menu.get(0);
    for(Food food : this.menu){
      if(food.getPrice() < lowest.getPrice()){
        lowest = food;
      }
    }
    return lowest;
  }

  @Override 
  public String toString(){
    String menu = "";
    int index = 1;
    for(Food food : this.menu){
      menu += index +" "+ food.toString() + "\n";
      index++;
    }
    return menu;
  }
}
