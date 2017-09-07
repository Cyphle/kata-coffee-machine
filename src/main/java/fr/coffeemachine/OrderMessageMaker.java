package fr.coffeemachine;

import fr.coffeemachine.drinks.Drink;

public class OrderMessageMaker implements MessageMaker {
  @Override
  public OrderMessage makeMessageForOrderOf(Drink drink) {
    return new OrderMessage("Drink maker makes 1 chocolate with no sugar - and therefore no stick");
  }
}
