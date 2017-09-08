package fr.coffeemachine.domain.utils;

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
    return amount.setScale(2, ROUND_FLOOR);
  }

  public Money add(Money amountToAdd) {
    return money.of(amount.add(amountToAdd.amount)).build();
  }

  public Money subtract(Money moneyToSubtract) {
    return money.of(amount.subtract(moneyToSubtract.amount)).build();
  }

  public Money multipleBy(int factor) {
    return money.of(amount.multiply(new BigDecimal(factor))).build();
  }

  public boolean isInferiorOrEqualAs(Money toCompare) {
    return amount.compareTo(toCompare.amount) <= 0;
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

    MoneyBuilder of(BigDecimal amount) {
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
