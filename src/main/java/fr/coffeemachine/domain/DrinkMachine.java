package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.infra.StatisticsPrinter;

public interface DrinkMachine {
  void orderDrinkOf(Drink drink);

  void orderChargedDrinkOf(Drink drink, Money money);
}
