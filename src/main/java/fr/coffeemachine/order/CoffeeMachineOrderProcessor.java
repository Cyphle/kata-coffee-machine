package fr.coffeemachine.order;

import fr.coffeemachine.drinks.Drink;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class CoffeeMachineOrderProcessor implements OrderProcessor {
  private final OrderMaker orderMaker;
  private final MessageMaker orderMessageMaker;

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

  @Override
  public String orderChargedDrink(Drink drink, BigDecimal money) {
    if (!drink.isEnoughToPay(money)) {
      OrderMessage orderMessage = orderMessageMaker.makeNotEnoughMoneyMessage(drink, money);
      return orderMessage.getMessageForDrinkMaker();
    }
    return null;
  }
}
