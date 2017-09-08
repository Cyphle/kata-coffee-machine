package fr.coffeemachine.domain.drinks;

public class Coffee extends Drink {
  private static int NUMBER_AVAILABLE_BEVERAGE = 10;

  @Override
  public String getDrinkType() {
    return "C";
  }

  @Override
  public String getDrinkName() {
    return "coffee";
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
