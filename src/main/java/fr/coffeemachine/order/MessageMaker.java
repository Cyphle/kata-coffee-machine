package fr.coffeemachine.order;

import fr.coffeemachine.Money;
import fr.coffeemachine.drinks.Drink;

import java.math.BigDecimal;

public interface MessageMaker {
  OrderMessage makeMessageForOrderOf(Drink drink);

  OrderMessage makeNotEnoughMoneyMessage(Drink drink, Money money);
}
