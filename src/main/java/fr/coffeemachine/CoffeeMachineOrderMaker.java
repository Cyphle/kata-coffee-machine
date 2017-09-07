package fr.coffeemachine;

import fr.coffeemachine.drinks.Drink;

import java.util.StringJoiner;

public class CoffeeMachineOrderMaker implements OrderMaker {
  @Override
  public String orderDrink(Drink drink) {
    StringJoiner order = new StringJoiner(":");
    order.add(drink.getDrinkType());

    if (drink.getNumberOfSugars() > 0) {
      order.add(Integer.toString(drink.getNumberOfSugars()));
      order.add("0");
    } else
      order.add(":");

    return order.toString();
  }
}