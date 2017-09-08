package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.drinks.Drink;

public interface OrderMaker {
  String createOrder(Drink drink);
}
