package fr.coffeemachine.domain;

import fr.coffeemachine.domain.statistics.Sale;
import fr.coffeemachine.infra.DateService;
import fr.coffeemachine.infra.StatisticsPrinter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoffeeMachineStatisticsBuilder implements StatisticsBuilder {
  private SaleRepository saleRepository;
  private DateService dateService;

  CoffeeMachineStatisticsBuilder(SaleRepository saleRepository, DateService dateService) {
    this.saleRepository = saleRepository;
    this.dateService = dateService;
  }

  @Override
  public void printStatistics(StatisticsPrinter printer) {
    Map<String, Integer> numberOfSalesByDrinkType = new HashMap<>();
    List<Sale> sales = saleRepository.getSalesOf(dateService.getTodayDate());
    sales.stream()
            .collect(Collectors.groupingBy(Sale::getDrinkType))
            .forEach((key, value) -> numberOfSalesByDrinkType.put(key, value.size()));
    printer.collectForPrint(numberOfSalesByDrinkType);
  }
}
