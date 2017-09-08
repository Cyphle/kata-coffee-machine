package fr.coffeemachine.infra.view;

import fr.coffeemachine.domain.order.Drink;
import fr.coffeemachine.domain.utils.Quantity;

import java.util.Map;

public interface StatisticsPrinter {
  void collectForPrint(Map<Drink, Quantity> sales);

  String flush();
}
