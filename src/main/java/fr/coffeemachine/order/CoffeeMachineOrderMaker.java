package fr.coffeemachine.order;

import fr.coffeemachine.order.drinks.Drink;

import java.util.StringJoiner;

public class CoffeeMachineOrderMaker implements OrderMaker {
  @Override
  public String createOrder(Drink drink) {
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