package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.drinks.DrinkEnum;

public interface BeverageQuantityChecker {
  @Deprecated
  boolean isEmpty(Drink drink);
  boolean isEmpty(DrinkEnum drink);
}
