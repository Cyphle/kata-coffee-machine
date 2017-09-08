package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.Money;
import fr.coffeemachine.domain.drinks.Drink;

import java.util.StringJoiner;

public class CoffeeMachineOrderProcessor implements OrderProcessor {
  private final OrderMaker orderMaker;
  private final MessageMaker orderMessageMaker;

  public CoffeeMachineOrderProcessor(OrderMaker orderMaker, MessageMaker orderMessageMaker) {
    this.orderMaker = orderMaker;
    this.orderMessageMaker = orderMessageMaker;
  }

  @Override
  public String makeDrinkOrder(Drink drink) {
    if (drink == null)
      return "";

    return orderMaker.createOrder(drink);
  }

  @Override
  public String makeOrderWithMessage(Drink drink) {
    StringJoiner orderWithMessage = new StringJoiner(" ");
    orderWithMessage.add(orderMaker.createOrder(drink));

    OrderMessage orderMessage = orderMessageMaker.makeMessageForOrderOf(drink);
    orderWithMessage.add(orderMessage.getMessageForDrinkMaker());

    return orderWithMessage.toString();
  }

  @Override
  public String makeOrderWithNotEnoughMoney(Drink drink, Money money) {
    OrderMessage orderMessage = orderMessageMaker.makeNotEnoughMoneyMessage(drink, money);
    return orderMessage.getMessageForDrinkMaker();
  }
}
