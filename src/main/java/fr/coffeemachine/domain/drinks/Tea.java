package fr.coffeemachine.domain.drinks;

public class Tea extends Drink {
  @Override
  public String getDrinkType() {
    return "T";
  }

  @Override
  public String getDrinkName() {
    return "tea";
  }
}
