package fr.coffeemachine.drinks;

import fr.coffeemachine.Money;

import java.math.BigDecimal;

import static fr.coffeemachine.Money.money;
import static fr.coffeemachine.drinks.SugarStatus.SUGAR_NOT_ALLOWED_FOR_THIS_DRINK;
import static fr.coffeemachine.drinks.SugarStatus.TOO_MUCH_SUGAR;

public abstract class Drink {
  private static final int MAX_SUGARS = 2;
  private BigDecimal price = new BigDecimal(0.4);
  private Money priceInMoney = money.of(0.4).build();
  private int numberOfSugars;

  public abstract String getDrinkType();

  public abstract String getDrinkName();

  public boolean canHaveSugar() {
    return true;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Money getPriceInMoney() {
    return priceInMoney;
  }

  public int getNumberOfSugars() {
    return numberOfSugars;
  }

  public SugarStatus addSugar(int numberOfSugars) {
    if (!canHaveSugar())
      return SUGAR_NOT_ALLOWED_FOR_THIS_DRINK;

    if (numberOfSugars > MAX_SUGARS)
      return TOO_MUCH_SUGAR;

    this.numberOfSugars = numberOfSugars;
    return SugarStatus.SUGAR_ADDED;
  }

  public boolean isEnoughToPay(Money money) {
    return priceInMoney.isLowerOrEqual(money);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Drink drink = (Drink) o;

    return numberOfSugars == drink.numberOfSugars;
  }

  @Override
  public int hashCode() {
    return numberOfSugars;
  }
}
