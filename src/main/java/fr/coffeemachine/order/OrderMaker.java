package fr.coffeemachine.order;

import fr.coffeemachine.order.drinks.Drink;

interface OrderMaker {
  String createOrder(Drink drink);
}
