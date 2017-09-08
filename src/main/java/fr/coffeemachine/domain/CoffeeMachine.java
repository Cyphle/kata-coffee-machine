package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.DrinkEnum;
import fr.coffeemachine.drinkmaker.DrinkMaker;
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
  public String orderDrinkOf(DrinkEnum drink, Money money) {
    if (!drink.isEnoughToPay(money))
      return orderProcessor.createOrderForNotEnoughMoney(drink, money);

    if (isEmpty(drink)) {
      notifyMissingDrink(drink);
      return orderProcessor.createOrderForBeverageShortage(drink);
    }

    drink.decreaseNumberAvailableDrink();
    saleRepository.addSell(drink);
    return orderProcessor.createOrderOf(drink);
  }

  @Override
  public void orderDrinkOf(Drink drink, Money money) {
    if (isEmpty(drink)) {
      processBeverageShortage(drink);
    } else if (drink.isEnoughToPay(money)) {
      processDrinkOrder(drink);
    } else {
      processNotEnoughMoney(drink, money);
    }
  }

  @Override
  public boolean isEmpty(Drink drink) {
    return drink.isEmpty();
  }

  @Override
  public boolean isEmpty(DrinkEnum drink) {
    return drink.isEmpty();
  }

  @Override
  public void notifyMissingDrink(Drink drink) {
    emailSender.sendBeverageShortageNotification(drink);
  }

  @Override
  public void notifyMissingDrink(DrinkEnum drink) {
    emailSender.sendBeverageShortageNotification(drink);
  }

  private void processNotEnoughMoney(Drink drink, Money money) {
    drinkMaker.takeOrderOf(orderProcessor.createOrderForNotEnoughMoney(drink, money));
  }

  private void processDrinkOrder(Drink drink) {
    drink.decreaseNumberAvailableBeverage();
    drinkMaker.takeOrderOf(orderProcessor.createOrderOf(drink));
    saleRepository.addSell(drink);
  }

  private void processBeverageShortage(Drink drink) {
    drinkMaker.takeOrderOf(orderProcessor.createOrderForBeverageShortage(drink));
    notifyMissingDrink(drink);
  }
}
