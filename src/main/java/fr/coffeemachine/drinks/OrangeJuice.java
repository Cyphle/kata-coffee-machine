package fr.coffeemachine.drinks;

import static fr.coffeemachine.Money.money;

public class OrangeJuice extends Drink {
  public OrangeJuice() {
    priceInMoney = money.of(0.6).build();
  }

  @Override
  public String getDrinkType() {
    return "O";
  }

  @Override
  public String getDrinkName() {
    return "orange juice";
  }

  @Override
  public boolean canHaveSugar() {
    return false;
  }
}
