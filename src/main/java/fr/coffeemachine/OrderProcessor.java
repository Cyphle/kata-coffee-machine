package fr.coffeemachine;

import fr.coffeemachine.drinks.Drink;

interface OrderProcessor {
  String orderDrink(Drink drink);

  String orderWithMessage(Drink drink);
}
