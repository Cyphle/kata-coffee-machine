package fr.coffeemachine;

import java.util.StringJoiner;

public class CoffeeMachineOrderProcessor implements OrderProcessor {
  @Override
  public String order(Drink drink) {
    if (drink == null)
      return "";

    StringJoiner order = new StringJoiner(":");
    order.add(drink.getDrinkType());
    if (drink.getNumberOfSugars() > 0)
      order.add(Integer.toString(drink.getNumberOfSugars()));

    return order.toString();
  }

  @Override
  public String orderDrink(Drink drink) {
    if (drink == null)
      return "";

    StringJoiner order = new StringJoiner(":");
    order.add(drink.getDrinkType());
    if (drink.getNumberOfSugars() > 0) {
      order.add(Integer.toString(drink.getNumberOfSugars()));
      order.add("0");
    }

    return order.toString();
  }
}
