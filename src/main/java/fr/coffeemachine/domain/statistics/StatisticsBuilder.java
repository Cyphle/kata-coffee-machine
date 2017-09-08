package fr.coffeemachine.domain.statistics;

import fr.coffeemachine.infra.view.StatisticsPrinter;

public interface StatisticsBuilder {
  void printStatisticsOfToday(StatisticsPrinter printer);
}
