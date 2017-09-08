package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.DrinkEnum;
import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.order.OrderProcessor;

public class CoffeeMachine implements DrinkMachine, BeverageQuantityChecker, EmailNotifier {
  private final OrderProcessor orderProcessor;
  private final SaleRepository saleRepository;
  private EmailSender emailSender;

  CoffeeMachine(OrderProcessor orderProcessor, SaleRepository saleRepository, EmailSender emailSender) {
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
  public boolean isEmpty(Drink drink) {
    return drink.isEmpty();
  }

  @Override
  public boolean isEmpty(DrinkEnum drink) {
    return drink.isEmpty();
  }

  @Override
  public void notifyMissingDrink(DrinkEnum drink) {
    emailSender.sendBeverageShortageNotification(drink);
  }
}
