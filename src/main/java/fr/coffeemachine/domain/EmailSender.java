package fr.coffeemachine.domain;

public interface EmailSender {
  void sendBeverageShortageNotification(Drink drink);
}
