package fr.coffeemachine;

import fr.coffeemachine.drinks.Drink;

import java.util.StringJoiner;

public class CoffeeMachineOrderProcessor implements OrderProcessor {
  @Override
  public String orderDrink(Drink drink) {
    if (drink == null)
      return "";

    StringJoiner order = new StringJoiner(":");
    order.add(drink.getDrinkType());

    if (drink.getNumberOfSugars() > 0) {
      order.add(Integer.toString(drink.getNumberOfSugars()));
      order.add("0");
    } else
      order.add(":");

    return order.toString();
  }

  @Override
  public String orderWithMessage(Drink drink) {
    StringJoiner orderWithMessage = new StringJoiner(" ");
    orderWithMessage.add(orderDrink(drink));

    if (drink.getDrinkType().equals("C"))
      orderWithMessage.add("M:Drink maker makes 1 tea with 1 sugar and a stick");
    else
      orderWithMessage.add("M:Drink maker makes 1 chocolate with no sugar - and therefore no stick");

    return orderWithMessage.toString();
  }
}
