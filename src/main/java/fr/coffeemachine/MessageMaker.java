package fr.coffeemachine;

import fr.coffeemachine.drinks.Drink;

public interface MessageMaker {
  OrderMessage makeMessageForOrderOf(Drink drink);
}
