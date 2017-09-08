package fr.coffeemachine.order;

import fr.coffeemachine.Money;
import fr.coffeemachine.drinks.Drink;

import static java.math.BigDecimal.ROUND_FLOOR;

public class CoffeeMachineMessageMaker implements MessageMaker {
  @Override
  public OrderMessage makeMessageForOrderOf(Drink drink) {
    return new OrderMessage(getBeginningOfSentence() +
            drink.getDrinkName() +
            getSugarSentence(drink.getNumberOfSugars()) +
            getStickSentence(drink.getNumberOfSugars()));
  }

  @Override
  public OrderMessage makeNotEnoughMoneyMessage(Drink drink, Money money) {
    OrderMessage orderMessage = new OrderMessage("Order for 1 " + drink.getDrinkName() + " at " + drink.getPrice().setScale(2, ROUND_FLOOR) + " euros is missing " + (drink.getPriceInMoney().subtract(money).getAmount().setScale(2, ROUND_FLOOR)) + " euros");
    return orderMessage;
  }

  private String getBeginningOfSentence() {
    return "Drink maker makes 1 ";
  }

  private String getSugarSentence(int numberOfSugars) {
    String sugarPart = " with ";
    sugarPart += numberOfSugars > 0 ? String.valueOf(numberOfSugars) : "no";
    sugarPart += " sugar" + (numberOfSugars > 1 ? "s" : "");
    return sugarPart;
  }

  private String getStickSentence(int numberOfSugars) {
    return numberOfSugars > 0 ? " and a stick" : " - and therefore no stick";
  }
}
