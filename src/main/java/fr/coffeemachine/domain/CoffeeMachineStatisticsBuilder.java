package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.statistics.Quantity;
import fr.coffeemachine.domain.statistics.Sale;
import fr.coffeemachine.infra.DateService;
import fr.coffeemachine.infra.view.StatisticsPrinter;

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
    Map<Drink, Quantity> salesByDrink = new HashMap<>();
    List<Sale> sales = saleRepository.getSalesOf(dateService.getTodayDate());
    sales.stream()
            .collect(Collectors.groupingBy(Sale::getDrink))
            .forEach((key, value) -> salesByDrink.put(key, new Quantity(value.size())));
    printer.collectForPrint(salesByDrink);
  }
}
