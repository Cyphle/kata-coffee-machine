package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.drinks.DrinkEnum;
import fr.coffeemachine.domain.statistics.Sale;

import java.util.Date;
import java.util.List;

public interface SaleRepository {
  @Deprecated
  void addSell(Drink drink);

  void addSell(DrinkEnum drink);

  List<Sale> getSalesOf(Date requiredDate);
}
