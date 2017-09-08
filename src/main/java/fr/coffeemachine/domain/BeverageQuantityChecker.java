package fr.coffeemachine.domain;

import fr.coffeemachine.domain.order.Drink;

public interface BeverageQuantityChecker {
  boolean isEmpty(Drink drink);
}
