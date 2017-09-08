package fr.coffeemachine.domain.drinks;

public class Tea extends Drink {
  private static int NUMBER_AVAILABLE_BEVERAGE = 10;

  @Override
  public String getDrinkType() {
    return "T";
  }

  @Override
  public String getDrinkName() {
    return "tea";
  }

  @Override
  public boolean isEmpty() {
    return NUMBER_AVAILABLE_BEVERAGE == 0;
  }
}
