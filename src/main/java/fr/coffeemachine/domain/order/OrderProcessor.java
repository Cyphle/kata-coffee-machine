package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.Money;
import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.drinks.DrinkEnum;

public interface OrderProcessor {
  String createOrderOf(DrinkEnum drink);

  String createOrderForNotEnoughMoney(DrinkEnum drink, Money money);

  String createOrderForBeverageShortage(DrinkEnum drink);
}
