import java.util.HashMap;
import java.util.Map;

// Create ShoppingBag class here
public class ShoppingBag<T extends PricedItem<Integer>>{
  private Map<T,Integer> shoppingBag = new HashMap<>();

  public void addItem(T item){
    if(shoppingBag.get(item) == null){
      shoppingBag.put(item,1);
    } else {
      shoppingBag.put(item,shoppingBag.get(item) + 1);
    }
  }

  public int getTotalPrice(){
    int total = 0;
    for(T item : shoppingBag.keySet()){
      total += item.getPrice() * shoppingBag.get(item);
    }
    return total;
  }
}
