package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.utils.Money;
import fr.coffeemachine.domain.utils.Quantity;

import java.math.BigDecimal;
import java.util.Arrays;

import static fr.coffeemachine.domain.utils.Money.money;

public enum AvailableDrink {
  COFFEE("C", money.of(0.4).build(), new Quantity(10)),
  TEA("T", money.of(0.4).build(), new Quantity(10)),
  CHOCOLATE("H", money.of(0.4).build(), new Quantity(1)),
  ORANGE_JUICE("O", money.of(0.6).build(), new Quantity(10));

  final String code;
  final Money price;
  final Quantity quantity;

  AvailableDrink(String code, Money price, Quantity quantity) {
    this.code = code;
    this.price = price;
    this.quantity = quantity;
  }

  boolean isEmpty() {
    return quantity.isZero();
  }

  String getDrinkName() {
    return Arrays.stream(toString().split("_"))
            .reduce("", (namePart, namePartTwo) -> namePart + " " + namePartTwo)
            .trim()
            .toLowerCase();
  }

  BigDecimal getPrice() {
    return price.getAmount();
  }

  Money calculateMissingMoney(Money paidAmount) {
    return price.subtract(paidAmount);
  }

  boolean isEnoughToPay(Money paidAmount) {
    return calculateMissingMoney(paidAmount).isInferiorOrEqualAs(money.of(0).build());
  }

  void decreaseNumberAvailableDrink() {
    quantity.decreaseBy(1);
  }
}