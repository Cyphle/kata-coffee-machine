package fr.coffeemachine.drinks;

import fr.coffeemachine.Money;
import fr.coffeemachine.order.OrderStatus;

import java.math.BigDecimal;

import static fr.coffeemachine.Money.money;
import static fr.coffeemachine.order.OrderStatus.TOO_MUCH_SUGAR;

public abstract class Drink {
  private static final int MAX_SUGARS = 2;
  private BigDecimal price = new BigDecimal(0.4);
  private Money priceInMoney = money.of(0.4).build();
  private int numberOfSugars;

  public abstract String getDrinkType();

  public abstract String getDrinkName();

  public BigDecimal getPrice() {
    return price;
  }

  public Money getPriceInMoney() {
    return priceInMoney;
  }

  public int getNumberOfSugars() {
    return numberOfSugars;
  }

  public OrderStatus addSugar(int numberOfSugars) {
    if (numberOfSugars > MAX_SUGARS)
      return TOO_MUCH_SUGAR;

    this.numberOfSugars = numberOfSugars;
    return OrderStatus.SUGAR_ADDED;
  }

  public boolean isEnoughToPay(BigDecimal money) {
    return price.compareTo(money) <= 0;
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
