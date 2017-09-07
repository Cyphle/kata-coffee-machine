package fr.coffeemachine;

import fr.coffeemachine.drinks.Drink;

public interface OrderMaker {
  String createOrder(Drink drink);
}
