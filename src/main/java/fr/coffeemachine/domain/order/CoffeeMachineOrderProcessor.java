package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.utils.Money;

public class CoffeeMachineOrderProcessor implements OrderProcessor {
  @Override
  public String createOrderOf(Drink drink) {
    if (drink == null)
      return "";

    return createOrder(drink) + createOrderMessage(drink);
  }

  @Override
  public String createOrderForNotEnoughMoney(Drink drink, Money money) {
    return "M:Order for 1 "
            + drink.getDrinkName()
            + " at "
            + drink.getPriceInMoney().getAmount()
            + " euros is missing "
            + drink.calculateMissingMoney(money).getAmount()
            + " euros";
  }

  @Override
  public String createOrderForBeverageShortage(Drink drink) {
    return "M:Sorry but " + drink.getDrinkName() + " is not available at the moment";
  }

  private String createOrder(Drink drink) {
    return drink.getTypeAndTemperature()
            + ":"
            + (drink.getNumberOfSugars() > 0 ? String.valueOf(drink.getNumberOfSugars()) + ":0" : ":");
  }

  private String createOrderMessage(Drink drink) {
    String message = " M:Drink maker makes 1 "
            + drink.getDrinkName();
    if (drink.canHaveSugarAndBeExtraHot())
      message += " with "
              + (drink.getNumberOfSugars() > 0 ? String.valueOf(drink.getNumberOfSugars()) : "no")
              + " sugar"
              + (drink.getNumberOfSugars() > 1 ? "s" : "")
              + (drink.getNumberOfSugars() > 0 ? " and a stick" : " - and therefore no stick");
    return message;
  }
}
