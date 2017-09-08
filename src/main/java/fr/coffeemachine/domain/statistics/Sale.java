package fr.coffeemachine.domain.statistics;

import fr.coffeemachine.domain.order.Drink;

public class Sale {
  private final Drink drink;

  public Sale(Drink drink) {
    this.drink = drink;
  }

  public Drink getDrink() {
    return drink;
  }
}
