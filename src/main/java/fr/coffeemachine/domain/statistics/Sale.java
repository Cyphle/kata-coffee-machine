package fr.coffeemachine.domain.statistics;

import fr.coffeemachine.domain.Drink;

import java.time.LocalDate;

public class Sale {
  private final LocalDate saleDate;
  private Drink drink;

  public Sale(LocalDate saleDate, Drink drink) {
    this.saleDate = saleDate;
    this.drink = drink;
  }

  public Drink getDrink() {
    return drink;
  }
}
