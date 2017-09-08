package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.Money;
import fr.coffeemachine.domain.drinks.DrinkEnum;

public class CoffeeMachineOrderProcessor implements OrderProcessor {
  private final OrderMaker orderMaker;
  private final MessageMaker orderMessageMaker;

  public CoffeeMachineOrderProcessor(OrderMaker orderMaker, MessageMaker orderMessageMaker) {
    this.orderMaker = orderMaker;
    this.orderMessageMaker = orderMessageMaker;
  }

  @Override
  public String createOrderOf(DrinkEnum drink) {
    if (drink == null)
      return "";

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
  public String createOrderForBeverageShortage(DrinkEnum drink) {
    return "M:Sorry but " + drink.getDrinkName() + " is not available at the moment";
  }
}
