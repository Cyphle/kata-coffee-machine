package fr.coffeemachine;

import fr.coffeemachine.drinks.Drink;

public class OrderMessageMaker implements MessageMaker {
  @Override
  public OrderMessage makeMessageForOrderOf(Drink drink) {
    return buildMessage(drink.getDrinkName(), drink.getNumberOfSugars());
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
