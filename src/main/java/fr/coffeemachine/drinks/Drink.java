package fr.coffeemachine.drinks;

import fr.coffeemachine.OrderStatus;

import static fr.coffeemachine.OrderStatus.TOO_MUCH_SUGAR;

public abstract class Drink {
  private static final int MAX_SUGARS = 2;
  private int numberOfSugars;

  public abstract String getDrinkType();

  public int getNumberOfSugars() {
    return numberOfSugars;
  }

  public OrderStatus addSugar(int numberOfSugars) {
    if (numberOfSugars > MAX_SUGARS)
      return TOO_MUCH_SUGAR;

    this.numberOfSugars = numberOfSugars;
    return OrderStatus.SUGAR_ADDED;
  }
}
