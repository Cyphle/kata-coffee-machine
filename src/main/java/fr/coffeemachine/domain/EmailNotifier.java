package fr.coffeemachine.domain;

public interface EmailNotifier {

  void notifyMissingDrink(Drink drink);
}
