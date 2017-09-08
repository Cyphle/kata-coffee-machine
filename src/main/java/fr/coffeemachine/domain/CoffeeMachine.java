package fr.coffeemachine.domain;

import fr.coffeemachine.domain.notification.EmailNotifier;
import fr.coffeemachine.domain.notification.EmailSender;
import fr.coffeemachine.domain.order.Drink;
import fr.coffeemachine.domain.order.OrderProcessor;
import fr.coffeemachine.domain.statistics.SaleRepository;
import fr.coffeemachine.domain.utils.Money;

public class CoffeeMachine implements DrinkMachine, BeverageQuantityChecker, EmailNotifier {
  private final OrderProcessor orderProcessor;
  private final SaleRepository saleRepository;
  private final EmailSender emailSender;

  CoffeeMachine(OrderProcessor orderProcessor, SaleRepository saleRepository, EmailSender emailSender) {
    this.orderProcessor = orderProcessor;
    this.saleRepository = saleRepository;
    this.emailSender = emailSender;
  }

  @Override
  public String orderDrinkOf(Drink drink, Money money) {
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
  public void notifyMissingDrink(Drink drink) {
    emailSender.sendBeverageShortageNotification(drink);
  }
}
