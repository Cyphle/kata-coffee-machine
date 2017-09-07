package fr.coffeemachine;

import fr.coffeemachine.drinks.Drink;

import java.util.StringJoiner;

public class CoffeeMachineOrderProcessor implements OrderProcessor {
  private MessageMaker orderMessageMaker;

  public CoffeeMachineOrderProcessor(MessageMaker orderMessageMaker) {
    this.orderMessageMaker = orderMessageMaker;
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
    } else
      order.add(":");

    return order.toString();
  }

  @Override
  public String orderWithMessage(Drink drink) {
    StringJoiner orderWithMessage = new StringJoiner(" ");
    orderWithMessage.add(orderDrink(drink));

    OrderMessage orderMessage = orderMessageMaker.makeMessageForOrderOf(drink);
    orderWithMessage.add(orderMessage.getMessageForDrinkMaker());

    return orderWithMessage.toString();
  }
}
