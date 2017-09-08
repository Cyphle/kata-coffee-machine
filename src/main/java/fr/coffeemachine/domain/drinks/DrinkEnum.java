package fr.coffeemachine.domain.drinks;

import fr.coffeemachine.domain.Money;
import fr.coffeemachine.domain.statistics.Quantity;

import java.math.BigDecimal;
import java.util.Arrays;

import static fr.coffeemachine.domain.Money.money;

public enum DrinkEnum {
  COFFEE("C", money.of(0.4).build(), true, true, new Quantity(10)),
  TEA("T", money.of(0.4).build(), true, true, new Quantity(10)),
  CHOCOLATE("H", money.of(0.4).build(), true, true, new Quantity(1)),
  ORANGE_JUICE("O", money.of(0.4).build(), false, false, new Quantity(10));

  public final String code;
  public final Money price;
  public final boolean canHaveSugar;
  public final boolean canBeExtraHot;
  public Quantity quantity;

  DrinkEnum(String code, Money price, boolean canHaveSugar, boolean canBeExtraHot, Quantity quantity) {
    this.code = code;
    this.price = price;
    this.canHaveSugar = canHaveSugar;
    this.canBeExtraHot = canBeExtraHot;
    this.quantity = quantity;
  }

  public boolean isEmpty() {
    return quantity.isZero();
  }

  public String getDrinkName() {
    return Arrays.asList(toString().split("_"))
            .stream()
            .reduce("", (namePart, namePartTwo) -> namePart + " " + namePartTwo)
            .trim()
            .toLowerCase();
  }

  public BigDecimal getPrice() {
    return price.getAmount();
  }

  public BigDecimal calculateMissingMoney(Money referenceMoney) {
    return price.subtract(referenceMoney).getAmount();
  }
}
