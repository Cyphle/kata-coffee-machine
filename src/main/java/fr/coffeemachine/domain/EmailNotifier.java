package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.Drink;

public interface EmailNotifier {
  void notifyMissingDrink(Drink drink);
}
