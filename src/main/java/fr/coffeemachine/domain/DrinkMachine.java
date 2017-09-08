package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.Drink;

interface DrinkMachine {
  void orderDrinkOf(Drink drink, Money money);
}
