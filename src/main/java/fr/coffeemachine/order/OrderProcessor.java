package fr.coffeemachine.order;

import fr.coffeemachine.order.drinks.Drink;

public interface OrderProcessor {
  String orderDrink(Drink drink);

  String orderWithMessage(Drink drink);
}
