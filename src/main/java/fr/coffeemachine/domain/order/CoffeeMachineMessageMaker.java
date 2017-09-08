package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.Money;
import fr.coffeemachine.domain.drinks.Drink;

import static java.math.BigDecimal.ROUND_FLOOR;

public class CoffeeMachineMessageMaker implements MessageMaker {
  @Override
  public OrderMessage makeMessageForOrderOf(Drink drink) {
    String message = getBeginningOfSentence() + drink.getDrinkName();
    if (drink.canHaveSugar())
      message += getSugarSentence(drink.getNumberOfSugars()) + getStickSentence(drink.getNumberOfSugars());
    return new OrderMessage(message);
  }

  @Override
  public OrderMessage makeNotEnoughMoneyMessage(Drink drink, Money money) {
    return new OrderMessage("Order for 1 " + drink.getDrinkName() + " at " + drink.getPrice().setScale(2, ROUND_FLOOR) + " euros is missing " + (drink.getPriceInMoney().subtract(money).getAmount().setScale(2, ROUND_FLOOR)) + " euros");
  }

  @Override
  public OrderMessage makeBeverageShortageMessage(Drink drink) {
    return new OrderMessage("M:Sorry but " + drink.getDrinkName() + " is not available at the moment");
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
