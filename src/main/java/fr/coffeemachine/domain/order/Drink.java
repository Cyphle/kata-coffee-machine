package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.utils.Money;

import java.math.BigDecimal;

import static fr.coffeemachine.domain.order.AvailableDrink.ORANGE_JUICE;

public class Drink implements Comparable<Drink> {
  private int sugarNumber = 0;
  private boolean isExtraHot = false;
  private final AvailableDrink drink;

  public Drink(AvailableDrink drink) {
    this.drink = drink;
  }

  public boolean isEnoughToPay(Money money) {
    return drink.isEnoughToPay(money);
  }

  public boolean isEmpty() {
    return drink.isEmpty();
  }

  public String getDrink() {
    return drink.toString();
  }

  public String getDrinkName() {
    return drink.getDrinkName();
  }

  BigDecimal getPrice() {
    return drink.getPrice();
  }

  public Money getPriceInMoney() {
    return drink.price;
  }

  String getTypeAndTemperature() {
    return drink.code + (isExtraHot ? "h" : "");
  }

  Money calculateMissingMoney(Money paidAmount) {
    return drink.calculateMissingMoney(paidAmount);
  }

  public void withSugar(int numberOfSugar) {
    if (canHaveSugarAndBeExtraHot())
      this.sugarNumber = numberOfSugar;
  }

  int getNumberOfSugars() {
    return sugarNumber;
  }

  boolean canHaveSugarAndBeExtraHot() {
    return drink != ORANGE_JUICE;
  }

  public void setExtraHot() {
    if (canHaveSugarAndBeExtraHot())
      this.isExtraHot = true;
  }

  @Override
  public int compareTo(Drink o) {
    return drink.toString().compareTo(o.drink.toString());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Drink drink = (Drink) o;

    if (sugarNumber != drink.sugarNumber) return false;
    if (isExtraHot != drink.isExtraHot) return false;
    return this.drink == drink.drink;
  }

  @Override
  public int hashCode() {
    int result = sugarNumber;
    result = 31 * result + (isExtraHot ? 1 : 0);
    result = 31 * result + (drink != null ? drink.hashCode() : 0);
    return result;
  }

  public void decreaseNumberAvailableDrink() {
    drink.decreaseNumberAvailableDrink();
  }
}
