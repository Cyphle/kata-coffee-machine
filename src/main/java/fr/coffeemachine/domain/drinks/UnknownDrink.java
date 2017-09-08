package fr.coffeemachine.domain.drinks;

public class UnknownDrink extends Drink {
  @Override
  String getDrinkType() {
    return "U";
  }

  @Override
  public String getDrinkName() {
    return "unknown";
  }
}
