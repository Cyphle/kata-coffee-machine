package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.Money;
import fr.coffeemachine.domain.Drink;

public interface OrderProcessor {
  String createOrderOf(Drink drink);

  String createOrderForNotEnoughMoney(Drink drink, Money money);

  String createOrderForBeverageShortage(Drink drink);
}
