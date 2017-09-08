package fr.coffeemachine.domain.drinks;

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
