package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.drinks.DrinkEnum;

interface DrinkMachine {
  @Deprecated
  void orderDrinkOf(Drink drink, Money money);

  String orderDrinkOf(DrinkEnum drink, Money money);
}
