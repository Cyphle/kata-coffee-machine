package fr.coffeemachine.infra.view;

import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.statistics.Quantity;

import java.util.Map;
import java.util.StringJoiner;

public class ConsoleStatisticsPrinter implements StatisticsPrinter {

  public static final String DELIMITER = " - ";

  @Override
  public void collectForPrint(Map<Drink, Quantity> sales) {
    // Sells : 1 coffee - 2 teas - 1 orange juice
//    Total : 1.8 euros
//    StringJoiner drinkSales = new StringJoiner(DELIMITER);
//    sales.entrySet()
//            .stream()
//            .forEach(sale -> drinkSales.add(sale.getValue() + " " + sale.getKey() + (sale.getValue() > 1 ? "s" : "")));
    throw new UnsupportedOperationException();
  }

  @Override
  public String flush() {
    throw new UnsupportedOperationException();
  }
}
