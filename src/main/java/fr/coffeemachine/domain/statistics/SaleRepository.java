package fr.coffeemachine.domain.statistics;

import fr.coffeemachine.domain.order.Drink;
import fr.coffeemachine.domain.statistics.Sale;

import java.util.Date;
import java.util.List;

public interface SaleRepository {
  void addSell(Drink drink);

  List<Sale> getSalesOf(Date requiredDate);
}
