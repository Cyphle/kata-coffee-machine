package fr.coffeemachine;

import fr.coffeemachine.drinks.Drink;

interface MessageMaker {
  OrderMessage makeMessageForOrderOf(Drink drink);
}
