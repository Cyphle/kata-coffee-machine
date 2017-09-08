package fr.coffeemachine.domain.drinkmaker;

public interface DrinkMaker {
  void makeDrinkFromOrder(String order);

  void takeOrderOf(String message);
}
