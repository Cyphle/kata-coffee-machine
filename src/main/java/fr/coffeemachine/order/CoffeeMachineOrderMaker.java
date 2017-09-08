package fr.coffeemachine.order;

import fr.coffeemachine.drinks.Drink;

public class CoffeeMachineOrderMaker implements OrderMaker {
  @Override
  public String createOrder(Drink drink) {
    Order order = new Order(drink);
    return order.createOrder();
  }
}