package fr.coffeemachine.domain.notification;

import fr.coffeemachine.domain.order.Drink;

public interface EmailNotifier {

  void notifyMissingDrink(Drink drink);
}
