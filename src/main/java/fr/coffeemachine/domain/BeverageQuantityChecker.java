package fr.coffeemachine.domain;

import fr.coffeemachine.domain.order.Drink;

interface BeverageQuantityChecker {
  boolean isEmpty(Drink drink);
}
