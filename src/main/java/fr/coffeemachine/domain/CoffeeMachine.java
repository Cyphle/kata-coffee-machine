package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinkmaker.DrinkMaker;
import fr.coffeemachine.domain.order.OrderProcessor;
import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.infra.StatisticsPrinter;

public class CoffeeMachine implements DrinkMachine {
  private DrinkMaker drinkMaker;
  private OrderProcessor orderProcessor;
  private SaleRepository saleRepository;

  CoffeeMachine(DrinkMaker drinkMaker, OrderProcessor orderProcessor, SaleRepository saleRepository) {
    this.drinkMaker = drinkMaker;
    this.orderProcessor = orderProcessor;
    this.saleRepository = saleRepository;
  }

  @Override
  public void orderDrinkOf(Drink drink) {
    drinkMaker.makeDrinkFromOrder(orderProcessor.makeDrinkOrder(drink));
  }

  @Override
  public void orderChargedDrinkOf(Drink drink, Money money) {
    if (drink.isEnoughToPay(money)) {
      drinkMaker.takeOrderOf(orderProcessor.makeOrderWithMessage(drink));
      saleRepository.addSell(drink);
    } else {
      drinkMaker.takeOrderOf(orderProcessor.makeOrderWithNotEnoughMoney(drink, money));
    }
  }
}
