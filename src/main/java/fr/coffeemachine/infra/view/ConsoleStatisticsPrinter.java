package fr.coffeemachine.infra.view;

import fr.coffeemachine.domain.utils.Money;
import fr.coffeemachine.domain.order.Drink;
import fr.coffeemachine.domain.utils.Quantity;

import java.util.Map;
import java.util.StringJoiner;

import static fr.coffeemachine.domain.utils.Money.money;
import static java.math.BigDecimal.ROUND_FLOOR;

public class ConsoleStatisticsPrinter implements StatisticsPrinter {
  private static final String DELIMITER = " - ";
  private StringBuilder buffer;

  public ConsoleStatisticsPrinter() {
    buffer = new StringBuilder();
  }

  @Override
  public void collectForPrint(Map<Drink, Quantity> sales) {
    setListOfSales(sales);
    buffer.append("\n");
    setTotalSales(sales);
  }

  @Override
  public String flush() {
    String printable = buffer.toString();
    buffer = new StringBuilder();
    return printable;
  }

  private void setListOfSales(Map<Drink, Quantity> sales) {
    buffer.append("Sells : ");
    StringJoiner drinkSales = new StringJoiner(DELIMITER);
    sales.forEach((key, value) -> drinkSales.add(value.getQuantity() + " " + key.getDrinkName() + (value.getQuantity() > 1 ? "s" : "")));
    buffer.append(drinkSales.toString());
  }

  private void setTotalSales(Map<Drink, Quantity> sales) {
    buffer.append("Total : ");
    Money total = sales.entrySet()
            .stream()
            .map(sale -> sale.getKey().getPriceInMoney().multipleBy(sale.getValue().getQuantity()))
            .reduce(money.of(0).build(), Money::add);
    buffer.append(total.getAmount().setScale(2, ROUND_FLOOR));
    buffer.append(" euros");
  }
}
