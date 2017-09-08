package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.Money;
import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.drinks.DrinkEnum;

public interface OrderProcessor {
  String makeDrinkOrder(Drink drink);

  String makeOrderWithMessage(Drink drink);

  @Deprecated
  String makeOrderWithNotEnoughMoney(Drink drink, Money money);

  String makeOrderWithNotEnoughMoney(DrinkEnum drink, Money money);

  String makeOrderWithBeverageShortage(Drink drink);
}
