package fr.coffeemachine;

import fr.coffeemachine.drinks.Drink;

interface OrderMaker {
  String createOrder(Drink drink);
}
