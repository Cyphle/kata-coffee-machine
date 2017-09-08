package fr.coffeemachine.domain.statistics;

import fr.coffeemachine.domain.SaleRepository;
import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.drinks.DrinkEnum;
import fr.coffeemachine.infra.DateService;
import fr.coffeemachine.infra.view.StatisticsPrinter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoffeeMachineStatisticsBuilder implements StatisticsBuilder {
  private final SaleRepository saleRepository;
  private final DateService dateService;

  public CoffeeMachineStatisticsBuilder(SaleRepository saleRepository, DateService dateService) {
    this.saleRepository = saleRepository;
    this.dateService = dateService;
  }

  @Override
  public void printStatisticsOfToday(StatisticsPrinter printer) {
    Map<DrinkEnum, Quantity> salesByDrink = new HashMap<>();
    List<Sale> sales = saleRepository.getSalesOf(dateService.getTodayDate());
    sales.stream()
            .collect(Collectors.groupingBy(Sale::getDrink))
            .forEach((key, value) -> salesByDrink.put(key, new Quantity(value.size())));
    printer.collectForPrint(salesByDrink);
  }
}
