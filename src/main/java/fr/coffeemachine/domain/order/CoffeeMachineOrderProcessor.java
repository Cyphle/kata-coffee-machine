package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.Money;
import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.drinks.DrinkEnum;

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
  public String createOrderOf(Drink drink) {
    StringJoiner orderWithMessage = new StringJoiner(" ");
    orderWithMessage.add(orderMaker.createOrder(drink));

    OrderMessage orderMessage = orderMessageMaker.makeMessageForOrderOf(drink);
    orderWithMessage.add(orderMessage.getMessageForDrinkMaker());

    return orderWithMessage.toString();
  }

  @Override
  public String createOrderOf(DrinkEnum drink) {
    String order = drink.getTypeAndTemperature()
            + ":"
            + (drink.getNumberOfSugars() > 0 ? String.valueOf(drink.getNumberOfSugars()) + ":0" : ":");

    order += " M:Drink maker makes 1 "
            + drink.getDrinkName();

    if (drink.canHaveSugarAndBeExtraHot())
      order += " with "
            + (drink.getNumberOfSugars() > 0 ? String.valueOf(drink.getNumberOfSugars()) : "no")
            + " sugar"
            + (drink.getNumberOfSugars() > 1 ? "s" : "")
            + (drink.getNumberOfSugars() > 0 ? " and a stick" : " - and therefore no stick");

    return order;
  }

  @Override
  public String createOrderForNotEnoughMoney(Drink drink, Money money) {
    OrderMessage orderMessage = orderMessageMaker.makeNotEnoughMoneyMessage(drink, money);
    return orderMessage.getMessageForDrinkMaker();
  }

  @Override
  public String createOrderForNotEnoughMoney(DrinkEnum drink, Money money) {
    return "M:Order for 1 "
            + drink.getDrinkName()
            + " at "
            + drink.getPrice()
            + " euros is missing "
            + drink.calculateMissingMoney(money).getAmount()
            + " euros";
  }

  @Override
  public String createOrderForBeverageShortage(Drink drink) {
    OrderMessage orderMessage = orderMessageMaker.makeBeverageShortageMessage(drink);
    return orderMessage.getMessageForDrinkMaker();
  }

  @Override
  public String createOrderForBeverageShortage(DrinkEnum drink) {
    return "M:Sorry but " + drink.getDrinkName() + " is not available at the moment";
  }
}
