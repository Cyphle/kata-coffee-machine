package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.Money;
import fr.coffeemachine.domain.drinks.Drink;

public interface MessageMaker {
  OrderMessage makeMessageForOrderOf(Drink drink);

  OrderMessage makeNotEnoughMoneyMessage(Drink drink, Money money);

  OrderMessage makeBeverageShortageMessage(Drink drink);
}
