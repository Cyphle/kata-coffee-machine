package fr.coffeemachine;

public interface OrderProcessor {
  String order(Drink drink);

  String orderDrink(Drink drink);
}
