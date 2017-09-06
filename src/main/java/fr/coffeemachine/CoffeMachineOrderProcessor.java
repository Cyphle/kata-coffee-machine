package fr.coffeemachine;

public class CoffeMachineOrderProcessor implements OrderProcessor {
  @Override
  public String order(Drink drink) {
    if (drink != null)
      return drink.getDrinkType();
    return "";
  }
}
