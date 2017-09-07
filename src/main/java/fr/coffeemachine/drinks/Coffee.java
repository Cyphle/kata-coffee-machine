package fr.coffeemachine.drinks;

public class Coffee extends Drink {
  @Override
  public String getDrinkType() {
    return "C";
  }

  @Override
  public String getDrinkName() {
    return "coffee";
  }
}
