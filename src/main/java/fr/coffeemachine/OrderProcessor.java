package fr.coffeemachine;

import fr.coffeemachine.drinks.Drink;

public interface OrderProcessor {
  String orderDrink(Drink drink);

  String orderWithMessage(Drink drink);
}
