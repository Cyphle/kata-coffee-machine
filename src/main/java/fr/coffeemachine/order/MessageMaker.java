package fr.coffeemachine.order;

import fr.coffeemachine.order.drinks.Drink;

interface MessageMaker {
  OrderMessage makeMessageForOrderOf(Drink drink);
}
