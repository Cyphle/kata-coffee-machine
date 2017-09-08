package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.DrinkEnum;

public interface EmailNotifier {

  void notifyMissingDrink(DrinkEnum drink);
}
