package fr.coffeemachine;

import fr.coffeemachine.drinkmaker.DrinkMaker;
import fr.coffeemachine.order.OrderProcessor;
import fr.coffeemachine.drinks.Drink;

import java.math.BigDecimal;

public class CoffeeMachine implements DrinkMachine {
  private DrinkMaker drinkMaker;
  private OrderProcessor orderProcessor;

  CoffeeMachine(DrinkMaker drinkMaker, OrderProcessor orderProcessor) {
    this.drinkMaker = drinkMaker;
    this.orderProcessor = orderProcessor;
  }

  @Override
  public void orderDrinkOf(Drink drink) {
    drinkMaker.makeDrinkFromOrder(orderProcessor.orderDrink(drink));
  }

  @Override
  public void orderChargedDrinkOf(Drink drink, BigDecimal money) {
    drinkMaker.forwardMissingMoneyMessageToClient(orderProcessor.orderChargedDrink(drink, money));
  }
}
