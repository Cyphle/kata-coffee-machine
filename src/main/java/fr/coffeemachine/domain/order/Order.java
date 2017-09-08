package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.drinks.Drink;

import java.util.StringJoiner;

class Order {
  private static final String DELIMITER = ":";
  private Drink drink;
  private StringJoiner order;

  Order(Drink drink) {
    this.drink = drink;
    order = new StringJoiner(DELIMITER);
  }

  String createOrder() {
    order.add(drink.getDrinkTypeAndTemperature());

    if (drink.getNumberOfSugars() > 0) {
      order.add(Integer.toString(drink.getNumberOfSugars()));
      order.add("0");
    } else
      order.add(":");

    return order.toString();
  }
}