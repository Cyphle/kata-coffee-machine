package fr.coffeemachine.infra.view;

import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.statistics.Quantity;

import java.util.Map;

public interface StatisticsPrinter {
  void collectForPrint(Map<Drink, Quantity> sales);

  String flush();
}
