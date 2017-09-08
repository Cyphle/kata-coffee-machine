package fr.coffeemachine.domain.notification;

import fr.coffeemachine.domain.order.Drink;

public interface EmailSender {
  void sendBeverageShortageNotification(Drink drink);
}
