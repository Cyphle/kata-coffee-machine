package fr.coffeemachine.domain.statistics;

import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.drinks.DrinkEnum;

import java.time.LocalDate;

public class Sale {
  private final LocalDate saleDate;
  private DrinkEnum drinkEnum;
  private Drink drink;

  public Sale(LocalDate saleDate, Drink drink) {
    this.saleDate = saleDate;
    this.drink = drink;
  }

  public Sale(LocalDate saleDate, DrinkEnum drinkEnum) {
    this.saleDate = saleDate;
    this.drinkEnum = drinkEnum;
  }

  public DrinkEnum getDrink() {
    return drinkEnum;
  }
}
