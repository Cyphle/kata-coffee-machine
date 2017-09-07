package fr.coffeemachine.order;

import fr.coffeemachine.drinks.Drink;

import java.math.BigDecimal;

public interface OrderProcessor {
  String orderDrink(Drink drink);

  String orderWithMessage(Drink drink);

  String orderChargedDrink(Drink drink, BigDecimal money);
}
