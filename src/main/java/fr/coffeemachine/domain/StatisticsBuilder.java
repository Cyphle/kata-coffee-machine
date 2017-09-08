package fr.coffeemachine.domain;

import fr.coffeemachine.infra.view.StatisticsPrinter;

public interface StatisticsBuilder {
  void printStatistics(StatisticsPrinter printer);
}
