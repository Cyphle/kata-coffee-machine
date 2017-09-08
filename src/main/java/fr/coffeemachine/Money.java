package fr.coffeemachine;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ROUND_FLOOR;

public class Money {
  public static final MoneyBuilder money = new MoneyBuilder();
  private final BigDecimal amount;

  private Money(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public boolean isLowerOrEqual(Money money) {
    return amount.compareTo(money.amount) <= 0;
  }

  public Money subtract(Money moneyToSubtract) {
    return money.of(amount.subtract(moneyToSubtract.amount)).build();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Money money = (Money) o;

    return amount != null ? amount.equals(money.amount) : money.amount == null;
  }

  @Override
  public int hashCode() {
    return amount != null ? amount.hashCode() : 0;
  }

  public static class MoneyBuilder {
    private BigDecimal amount;

    public MoneyBuilder of(BigDecimal amount) {
      this.amount = amount;
      return this;
    }

    public MoneyBuilder of(double amount) {
      this.amount = new BigDecimal(amount, MathContext.DECIMAL64).setScale(2, ROUND_FLOOR);
      return this;
    }

    public Money build() {
      return new Money(amount);
    }
  }
}
