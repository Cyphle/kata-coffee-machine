package fr.coffeemachine.order;

import fr.coffeemachine.drinks.Drink;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_FLOOR;

public class CoffeeMachineMessageMaker implements MessageMaker {
  @Override
  public OrderMessage makeMessageForOrderOf(Drink drink) {
    return buildMessage(drink.getDrinkName(), drink.getNumberOfSugars());
  }

  @Override
  public OrderMessage makeNotEnoughMoneyMessage(Drink drink, BigDecimal money) {
    return new OrderMessage("Order for 1 " + drink.getDrinkName() + " at " + drink.getPrice().setScale(2, ROUND_FLOOR) + " euros is missing " + (drink.getPrice().subtract(money).setScale(2, ROUND_FLOOR)) + " euros");
  }

  private OrderMessage buildMessage(String drinkName, int numberOfSugars) {
    return new OrderMessage(getBeginningOfSentence() +
            drinkName +
            getSugarSentence(numberOfSugars) +
            getStickSentence(numberOfSugars));
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
