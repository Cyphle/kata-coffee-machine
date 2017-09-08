package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.Drink;

public interface EmailSender {
  void sendBeverageShortageNotification(Drink drink);
}
