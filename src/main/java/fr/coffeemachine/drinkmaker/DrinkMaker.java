package fr.coffeemachine.drinkmaker;

public interface DrinkMaker {
  void makeDrinkFromOrder(String order);

  void forwardMissingMoneyMessageToClient(String message);
}
