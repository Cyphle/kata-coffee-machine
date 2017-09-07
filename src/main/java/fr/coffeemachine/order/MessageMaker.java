package fr.coffeemachine.order;

import fr.coffeemachine.drinks.Drink;

import java.math.BigDecimal;

public interface MessageMaker {
  OrderMessage makeMessageForOrderOf(Drink drink);

  OrderMessage makeNotEnoughMoneyMessage(Drink drink, BigDecimal money);
}
