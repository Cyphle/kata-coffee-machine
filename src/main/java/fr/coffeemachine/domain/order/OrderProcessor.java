package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.Money;
import fr.coffeemachine.domain.drinks.Drink;

public interface OrderProcessor {
  String makeDrinkOrder(Drink drink);

  String makeOrderWithMessage(Drink drink);

  String makeOrderWithNotEnoughMoney(Drink drink, Money money);

  String makeOrderWithBeverageShortage(Drink drink);
}
