package fr.coffeemachine;

import fr.coffeemachine.drinks.Drink;

import java.math.BigDecimal;

public interface DrinkMachine {
  void orderDrinkOf(Drink drink);

  void orderChargedDrinkOf(Drink drink, BigDecimal money);
}
