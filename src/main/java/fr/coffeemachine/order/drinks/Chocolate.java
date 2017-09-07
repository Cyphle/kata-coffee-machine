package fr.coffeemachine.order.drinks;

public class Chocolate extends Drink {
  @Override
  public String getDrinkType() {
    return "H";
  }

  @Override
  public String getDrinkName() {
    return "chocolate";
  }
}
