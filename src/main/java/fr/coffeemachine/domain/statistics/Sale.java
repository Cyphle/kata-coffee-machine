package fr.coffeemachine.domain.statistics;

import fr.coffeemachine.domain.drinks.DrinkEnum;

import java.time.LocalDate;

public class Sale {
  private final LocalDate saleDate;
  private DrinkEnum drink;

  public Sale(LocalDate saleDate, DrinkEnum drink) {
    this.saleDate = saleDate;
    this.drink = drink;
  }

  public DrinkEnum getDrink() {
    return drink;
  }
}
