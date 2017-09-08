package fr.coffeemachine.domain.drinks;

import fr.coffeemachine.domain.Money;

import java.math.BigDecimal;

import static fr.coffeemachine.domain.Money.money;
import static fr.coffeemachine.domain.drinks.SugarStatus.SUGAR_NOT_ALLOWED_FOR_THIS_DRINK;
import static fr.coffeemachine.domain.drinks.SugarStatus.TOO_MUCH_SUGAR;

public abstract class Drink {
  private static final int MAX_SUGARS = 2;
  private int numberOfSugars;
  private boolean isExtraHot = false;
  Money priceInMoney = money.of(0.4).build();

  abstract String getDrinkType();

  public abstract String getDrinkName();

  public boolean canHaveSugar() {
    return true;
  }

  public BigDecimal getPrice() {
    return priceInMoney.getAmount();
  }

  public Money getPriceInMoney() {
    return priceInMoney;
  }

  public boolean isEnoughToPay(Money money) {
    return priceInMoney.isLowerOrEqual(money);
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

  boolean canBeExtraHot() {
    return true;
  }

  public void setExtraHot() {
    if (canBeExtraHot())
      isExtraHot = true;
  }

  public String getDrinkTypeAndTemperature() {
    return getDrinkType() + (isExtraHot ? "h" : "");
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
