package fr.coffeemachine.infra;

import java.util.Map;

public interface StatisticsPrinter {
  void collectForPrint(String toPrint);

  void collectForPrint(Map<String, Integer> sales);

  String flush();
}
