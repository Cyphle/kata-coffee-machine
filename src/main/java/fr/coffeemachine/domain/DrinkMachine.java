package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.DrinkEnum;

interface DrinkMachine {
  String orderDrinkOf(DrinkEnum drink, Money money);
}
