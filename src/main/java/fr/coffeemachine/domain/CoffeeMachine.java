package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinkmaker.DrinkMaker;
import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.order.OrderProcessor;

public class CoffeeMachine implements DrinkMachine, BeverageQuantityChecker, EmailNotifier {
  private final DrinkMaker drinkMaker;
  private final OrderProcessor orderProcessor;
  private final SaleRepository saleRepository;
  private EmailSender emailSender;

  CoffeeMachine(DrinkMaker drinkMaker, OrderProcessor orderProcessor, SaleRepository saleRepository, EmailSender emailSender) {
    this.drinkMaker = drinkMaker;
    this.orderProcessor = orderProcessor;
    this.saleRepository = saleRepository;
    this.emailSender = emailSender;
  }

  @Override
  public void orderChargedDrinkOf(Drink drink, Money money) {
    if (isEmpty(drink)) {
      drinkMaker.takeOrderOf(orderProcessor.makeOrderWithBeverageShortage(drink));
      notifyMissingDrink(drink);
    } else if (drink.isEnoughToPay(money)) {
      drink.decreaseNumberAvailableBeverage();
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

  @Override
  public void notifyMissingDrink(Drink drink) {
    emailSender.sendBeverageShortageNotification(drink);
  }
}
