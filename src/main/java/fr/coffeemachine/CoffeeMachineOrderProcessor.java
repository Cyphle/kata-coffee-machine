package fr.coffeemachine;

import fr.coffeemachine.drinks.Drink;

import java.util.StringJoiner;

public class CoffeeMachineOrderProcessor implements OrderProcessor {
  private OrderMaker orderMaker;
  private MessageMaker orderMessageMaker;

  public CoffeeMachineOrderProcessor(OrderMaker orderMaker, MessageMaker orderMessageMaker) {
    this.orderMaker = orderMaker;
    this.orderMessageMaker = orderMessageMaker;
  }

  @Override
  public String orderDrink(Drink drink) {
    if (drink == null)
      return "";

    return orderMaker.createOrder(drink);
  }

  @Override
  public String orderWithMessage(Drink drink) {
    StringJoiner orderWithMessage = new StringJoiner(" ");
    orderWithMessage.add(orderMaker.createOrder(drink));

    OrderMessage orderMessage = orderMessageMaker.makeMessageForOrderOf(drink);
    orderWithMessage.add(orderMessage.getMessageForDrinkMaker());

    return orderWithMessage.toString();
  }
}
