package fr.coffeemachine.drinkmaker;

public interface DrinkMaker {
  void makeDrinkFromOrder(String order);

  void takeOrderOf(String message);
}
