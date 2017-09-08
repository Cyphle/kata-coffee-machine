package fr.coffeemachine.domain.drinks;

public class Chocolate extends Drink {
  private static int NUMBER_AVAILABLE_BEVERAGE = 1;

  @Override
  public String getDrinkType() {
    return "H";
  }

  @Override
  public String getDrinkName() {
    return "chocolate";
  }

  @Override
  public boolean isEmpty() {
    return NUMBER_AVAILABLE_BEVERAGE == 0;
  }

  @Override
  public void decreaseNumberAvailableBeverage() {
    --NUMBER_AVAILABLE_BEVERAGE;
  }
}
