package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.drinks.DrinkEnum;

public interface EmailSender {
  @Deprecated
  void sendBeverageShortageNotification(Drink drink);

  void sendBeverageShortageNotification(DrinkEnum drink);
}
