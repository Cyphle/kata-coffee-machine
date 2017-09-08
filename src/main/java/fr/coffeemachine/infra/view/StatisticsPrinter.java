package fr.coffeemachine.infra.view;

import fr.coffeemachine.domain.drinks.DrinkEnum;
import fr.coffeemachine.domain.statistics.Quantity;

import java.util.Map;

public interface StatisticsPrinter {
  void collectForPrint(Map<DrinkEnum, Quantity> sales);

  String flush();
}
