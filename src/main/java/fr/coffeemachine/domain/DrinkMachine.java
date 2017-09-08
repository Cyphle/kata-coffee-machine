package fr.coffeemachine.domain;

import fr.coffeemachine.domain.order.Drink;
import fr.coffeemachine.domain.utils.Money;

interface DrinkMachine {
  String orderDrinkOf(Drink drink, Money money);
}
