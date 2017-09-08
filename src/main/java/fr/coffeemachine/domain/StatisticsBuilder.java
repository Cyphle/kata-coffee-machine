package fr.coffeemachine.domain;

import fr.coffeemachine.infra.StatisticsPrinter;

public interface StatisticsBuilder {
  void printStatistics(StatisticsPrinter printer);
}
