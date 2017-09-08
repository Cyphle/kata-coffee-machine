package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinkmaker.DrinkMaker;
import fr.coffeemachine.domain.order.OrderProcessor;
import fr.coffeemachine.domain.drinks.Drink;

public class CoffeeMachine implements DrinkMachine, BeverageQuantityChecker {
  private final DrinkMaker drinkMaker;
  private final OrderProcessor orderProcessor;
  private final SaleRepository saleRepository;

  CoffeeMachine(DrinkMaker drinkMaker, OrderProcessor orderProcessor, SaleRepository saleRepository) {
    this.drinkMaker = drinkMaker;
    this.orderProcessor = orderProcessor;
    this.saleRepository = saleRepository;
  }

  @Override
  public void orderChargedDrinkOf(Drink drink, Money money) {
    if (isEmpty(drink))
      drinkMaker.takeOrderOf(orderProcessor.makeOrderWithBeverageShortage(drink));
    else if (drink.isEnoughToPay(money)) {
      drinkMaker.takeOrderOf(orderProcessor.makeOrderWithMessage(drink));
      saleRepository.addSell(drink);
    } else {
      drinkMaker.takeOrderOf(orderProcessor.makeOrderWithNotEnoughMoney(drink, money));
    }
  }

  @Override
  public boolean isEmpty(Drink drink) {
    return drink.isEmpty();
  }
}
