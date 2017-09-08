package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.Drink;

public interface SaleRepository {
  void addSell(Drink drink);
}
