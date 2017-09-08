package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.drinks.DrinkEnum;

public interface EmailNotifier {
  @Deprecated
  void notifyMissingDrink(Drink drink);

  void notifyMissingDrink(DrinkEnum drink);
}
