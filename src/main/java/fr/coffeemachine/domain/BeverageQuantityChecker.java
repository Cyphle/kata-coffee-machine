package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.Drink;

public interface BeverageQuantityChecker {
  boolean isEmpty(Drink drink);
}
